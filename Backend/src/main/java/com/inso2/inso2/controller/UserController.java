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
import com.inso2.inso2.service.user.LoadUserService;
import com.inso2.inso2.service.user.RegisterUserService;
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

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtTokenUtils;

    private final MyUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final LoadUserService loadUserService;

    private final RegisterUserService registerUserService;

    public UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtTokenUtils, MyUserDetailsService userDetailsService, UserRepository userRepository, RoleRepository roleRepository, LoadUserService loadUserService, RegisterUserService registerUserService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.loadUserService = loadUserService;
        this.registerUserService = registerUserService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) throws Exception {
        try {
           registerUserService.register(req);
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(req.getEmail());

            final String jwt = jwtTokenUtils.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getAuthorities().iterator().next()));
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "Cannot register, email just registered",
                    HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());

            final String jwt = jwtTokenUtils.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getAuthorities().iterator().next()));
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    "Invalid username or password",
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody UserUpdateRequest req) throws Exception {

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
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
            if(user.getAddress() == null || !user.getAddress().equals(req.getAddress())){
                if (req.getAddress() == null || req.getAddress().isBlank()){
                    user.setAddress(null);
                }
                else{
                    user.setAddress(req.getAddress());
                }
            }
            if(user.getCountry() == null || !user.getCountry().equals(req.getCountry())){
                if (req.getCountry() == null || req.getCountry().isBlank()){
                    user.setCountry(null);
                }
                else{
                    user.setCountry(req.getCountry());
                }
            }
            if(user.getZipCode() == null || !user.getZipCode().equals(req.getZipCode())){
                if (req.getZipCode() == null || req.getZipCode().isBlank()){
                    user.setZipCode(null);
                }
                else{
                    user.setZipCode(req.getZipCode());
                }
            }
            if(user.getPhoneNumber() == null || !user.getPhoneNumber().equals(req.getPhoneNumber())){
                if (req.getPhoneNumber() == null || req.getPhoneNumber().isBlank()){
                    user.setPhoneNumber(null);
                }
                else{
                    user.setPhoneNumber(req.getPhoneNumber());
                }
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
            User user = loadUserService.load(auth);
            return ResponseEntity.ok(new UserDataResponse().build(user));
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
