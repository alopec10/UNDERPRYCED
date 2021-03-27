package com.inso2.inso2.controller;

import com.inso2.inso2.dto.AuthenticationRequest;
import com.inso2.inso2.dto.AuthenticationResponse;
import com.inso2.inso2.dto.RegisterRequest;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtTokenUtils;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
}
