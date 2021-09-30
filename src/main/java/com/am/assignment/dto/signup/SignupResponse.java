package com.am.assignment.dto.signup;

import lombok.Data;

import java.util.List;

@Data
public class SignupResponse {
    private String username;
    private String password;
    private List<String> permissions;
}
