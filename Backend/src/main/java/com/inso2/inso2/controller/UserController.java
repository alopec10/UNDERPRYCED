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
import com.inso2.inso2.service.user.GenerateAuthenticationTokenService;
import com.inso2.inso2.service.user.LoadUserService;
import com.inso2.inso2.service.user.RegisterUserService;
import com.inso2.inso2.service.user.UpdateUserService;
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

    private final LoadUserService loadUserService;

    private final RegisterUserService registerUserService;

    private final UpdateUserService updateUserService;

    private final GenerateAuthenticationTokenService generateAuthenticationTokenService;

    public UserController(AuthenticationManager authenticationManager, LoadUserService loadUserService, RegisterUserService registerUserService, UpdateUserService updateUserService, GenerateAuthenticationTokenService generateAuthenticationTokenService) {
        this.authenticationManager = authenticationManager;
        this.loadUserService = loadUserService;
        this.registerUserService = registerUserService;
        this.updateUserService = updateUserService;
        this.generateAuthenticationTokenService = generateAuthenticationTokenService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) throws Exception {
        try {
           registerUserService.register(req);
            return ResponseEntity.ok(generateAuthenticationTokenService.generate(req.getEmail()));
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    "Cannot register, validation problems",
                    HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            return ResponseEntity.ok(generateAuthenticationTokenService.generate(authenticationRequest.getEmail()));
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
            updateUserService.update(req, user);
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
