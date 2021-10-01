package com.am.assignment.serviceImpl;


import com.am.assignment.dto.auth.AuthenticationRequest;
import com.am.assignment.dto.auth.AuthenticationResponse;
import com.am.assignment.dto.signup.SignupRequest;
import com.am.assignment.dto.signup.SignupResponse;
import com.am.assignment.entity.Role;
import com.am.assignment.entity.User;
import com.am.assignment.exception.UserExistsException;
import com.am.assignment.repository.UserRepository;
import com.am.assignment.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public AuthenticationResponse signin(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwt, authenticationRequest.getUsername(), (1000 * 60) * 60); //1H = (1000 * 60) * 60
        return authenticationResponse;
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        User user = userRepository.findByUsername(signupRequest.getUsername());
        if (user != null) {
            throw new UserExistsException("Already exists!!");
        }
        user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        List<Role> roles = Objects.nonNull(signupRequest.getPermissions()) ? signupRequest.getPermissions().stream().map(permission -> roleServiceImpl.findByName(permission)).collect(Collectors.toList()) : new ArrayList<>();
        user.setRoles(roles);
        user = userRepository.save(user);

        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setUsername(user.getUsername());
        signupResponse.setPassword(user.getPassword());
        signupResponse.setPermissions(signupRequest.getPermissions());
        return signupResponse;
    }
}

