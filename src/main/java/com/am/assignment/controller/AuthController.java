package com.am.assignment.controller;


import com.am.assignment.dto.auth.AuthenticationRequest;
import com.am.assignment.dto.auth.AuthenticationResponse;
import com.am.assignment.dto.signup.SignupRequest;
import com.am.assignment.dto.signup.SignupResponse;
import com.am.assignment.entity.User;
import com.am.assignment.serviceImpl.MyUserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auth")
@Api(tags = "AuthController")
public class AuthController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) throws Exception {
        return ResponseEntity.ok().body(myUserDetailsService.signup(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok().body(myUserDetailsService.signin(authenticationRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> get() {
        return ResponseEntity.ok().body(myUserDetailsService.findAll());
    }
}
