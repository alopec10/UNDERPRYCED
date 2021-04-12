package com.inso2.inso2.controller;

import com.inso2.inso2.dto.authentication.AuthenticationRequest;
import com.inso2.inso2.dto.authentication.AuthenticationResponse;
import com.inso2.inso2.dto.register.RegisterRequest;
import com.inso2.inso2.dto.user.data.UserDataResponse;
import com.inso2.inso2.dto.user.update.UserUpdateRequest;
import com.inso2.inso2.model.Role;
import com.inso2.inso2.model.RoleName;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.RoleRepository;
import com.inso2.inso2.repository.UserRepository;
import com.inso2.inso2.security.JwtUtils;
import com.inso2.inso2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private JwtUtils jwtTokenUtils;

    private MyUserDetailsService userDetailsService;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtTokenUtils, MyUserDetailsService userDetailsService, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) throws Exception {
        try {
           User u = new User();
           u.setName(req.getName());
           u.setSurname(req.getSurname());
           u.setEmail(req.getEmail());
           u.setPassword(passwordEncoder.encode(req.getPassword()));
           u.setAddress(req.getAddress());
           u.setCountry(req.getCountry());
           u.setZipCode(req.getZipCode());
           u.setPhoneNumber(req.getPhoneNumber());
           Set<String> strRoles = req.getRole();
           Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
                roles.add(userRole);
            }
            else {
                strRoles.forEach(role -> {
                    if (role.equals("admin")) {
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);
                        roles.add(adminRole);

                    }
                    else {
                            Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
                            roles.add(userRole);
                    }
                });
            }
            u.setRoles(roles);
           userRepository.save(u);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "Cannot register, email just registered",
                    HttpStatus.CONFLICT);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(req.getEmail());

        final String jwt = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    "Invalid username or password",
                    HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody UserUpdateRequest req) throws Exception {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            if(!user.getName().equals(req.getName())){
                user.setName(req.getName());
            }
            if(!user.getSurname().equals(req.getSurname())){
                user.setSurname(req.getSurname());
            }
            if(!user.getEmail().equals(req.getEmail())){
                user.setEmail(req.getEmail());
            }
            String new_pass = passwordEncoder.encode(req.getPassword());
            if(req.getPassword() != null && !req.getPassword().equals("") && !user.getPassword().equals(new_pass)){
                user.setPassword(new_pass);
            }
            if(!user.getAddress().equals(req.getAddress())){
                user.setAddress(req.getAddress());
            }
            if(!user.getCountry().equals(req.getCountry())){
                user.setCountry(req.getCountry());
            }
            if(!user.getZipCode().equals(req.getZipCode())){
                user.setZipCode(req.getZipCode());
            }
            if(!user.getPhoneNumber().equals(req.getPhoneNumber())){
                user.setPhoneNumber(req.getPhoneNumber());
            }
            userRepository.save(user);
            return ResponseEntity.ok("User updated");
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<?> data() throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            return ResponseEntity.ok(new UserDataResponse().build(user));
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
