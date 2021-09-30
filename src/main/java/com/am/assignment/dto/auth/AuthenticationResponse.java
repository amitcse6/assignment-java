package com.am.assignment.dto.auth;

import lombok.Data;


@Data
public class AuthenticationResponse {
    private final String jwt;
    private final String username;
    private final String idToken;
    private final long expiresIn;
}
