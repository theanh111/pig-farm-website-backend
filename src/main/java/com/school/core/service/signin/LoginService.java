package com.school.core.service.signin;

import com.school.core.entity.LoginRequest;
import com.school.core.response.AppResponse;
import com.school.core.security.jwt.TokenProvider;
import com.school.core.service.tokenchecker.JWTService;
import com.school.core.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LoginService {
    private final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        List<String> data = new ArrayList<>();
        String checkUserName = userService.findUsernameByUsername(loginRequest.getUsername());
        if (checkUserName == null) {
            return new ResponseEntity<>(new AppResponse().badRequest("This username: " + loginRequest.getUsername() + " is NOT exist!"), HttpStatus.BAD_REQUEST);
        } else {
            String passwordByUsername = userService.findUserPasswordByUsername(loginRequest.getUsername());
            if (passwordByUsername == null) {
                return new ResponseEntity<>(new AppResponse().badRequest("This username: " + loginRequest.getUsername() + " is NOT exist!"), HttpStatus.BAD_REQUEST);
            } else {
                boolean checkIfPasswordMatches = bCryptPasswordEncoder.matches(loginRequest.getPassword(), passwordByUsername);
                if (checkIfPasswordMatches) {
                    String emailUser = userService.findUserEmailByUsername(loginRequest.getUsername());
                    if (emailUser != null && emailUser.length() > 0) {
                        boolean isUserConfirmEmail = userService.isUserConfirmEmail(emailUser);
                        if (isUserConfirmEmail) {
                            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                            String token = tokenProvider.createToken(authentication, true);
                            data.add(token);
                            log.info("--Username--: " + loginRequest.getUsername() + " LOGGED!");
                            return new ResponseEntity<>(new AppResponse().successWithData("Login Successfully!", data), HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(new AppResponse().badRequest("User is not confirm email! Please check email!"), HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>(new AppResponse().badRequest("Can't find email with this username!"), HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>(new AppResponse().badRequest("Wrong password!"), HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}
