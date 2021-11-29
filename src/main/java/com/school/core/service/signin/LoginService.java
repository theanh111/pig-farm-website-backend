package com.school.core.service.signin;

import com.school.core.entity.LoginRequest;
import com.school.core.response.AppResponse;
import com.school.core.security.jwt.TokenProvider;
import com.school.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        String checkUserName = userService.findUsername(loginRequest.getUsername());
        if (checkUserName == null) {
            return new ResponseEntity<>(new AppResponse().badRequest("This username: " + loginRequest.getUsername() + " is NOT exist!"), HttpStatus.BAD_REQUEST);
        } else {
            String passwordByUsername = userService.findUserPassword(loginRequest.getUsername());
            if (passwordByUsername == null) {
                return new ResponseEntity<>(new AppResponse().badRequest("This username: " + loginRequest.getUsername() + " is NOT exist!"), HttpStatus.BAD_REQUEST);
            } else {
                boolean checkIfPasswordMatches = bCryptPasswordEncoder.matches(loginRequest.getPassword(), passwordByUsername);
                if (checkIfPasswordMatches) {
                    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                    String token = tokenProvider.createToken(authentication, true);
                    return new ResponseEntity<>(new AppResponse().success("Welcome " + loginRequest.getUsername() + "! Token: [" + token + "]"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new AppResponse().badRequest("Wrong password!"), HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}
