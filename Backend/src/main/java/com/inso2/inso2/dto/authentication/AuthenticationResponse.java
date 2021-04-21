package com.inso2.inso2.dto.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final String role;

    public AuthenticationResponse(String jwt, GrantedAuthority role) {
        this.jwt = jwt;
        this.role = role.toString();
    }

    public String getJwt() {
        return jwt;
    }

    public String getRole() {
        return role;
    }
}
