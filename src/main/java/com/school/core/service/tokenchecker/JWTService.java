package com.school.core.service.tokenchecker;

import com.school.core.entity.LoginRequest;
import com.school.core.response.AppResponse;
import com.school.core.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
    @Autowired
    private TokenProvider tokenProvider;


    public ResponseEntity<?> checkToken(String token) {
        boolean checkToken =  tokenProvider.validateToken(token);
        if (checkToken) {
            return new ResponseEntity<>(new AppResponse().success("This user passed!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AppResponse().badRequest("This user failed!"), HttpStatus.BAD_REQUEST);
        }
    }
}
