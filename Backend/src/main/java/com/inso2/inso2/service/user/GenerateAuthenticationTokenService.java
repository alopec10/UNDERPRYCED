package com.inso2.inso2.service.user;

import com.inso2.inso2.dto.authentication.AuthenticationResponse;
import com.inso2.inso2.security.JwtUtils;
import com.inso2.inso2.service.MyUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GenerateAuthenticationTokenService {

    private final JwtUtils jwtTokenUtils;

    private final MyUserDetailsService userDetailsService;

    public GenerateAuthenticationTokenService(JwtUtils jwtTokenUtils, MyUserDetailsService userDetailsService) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponse generate(String email){
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(email);

        final String jwt = jwtTokenUtils.generateToken(userDetails);

        return new AuthenticationResponse(jwt, userDetails.getAuthorities().iterator().next());
    }
}
