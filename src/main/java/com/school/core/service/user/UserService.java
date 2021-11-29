package com.school.core.service.user;

import com.school.core.entity.User;
import com.school.core.entity.UserPrinciple;
import com.school.core.repository.UserRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import com.school.core.service.confirm.ConfirmTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> save(User user) {
        try {
            userRepository.saveUser(user);
            return new ResponseEntity<>(new AppResponse().success("Register Successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Can't Register!"), HttpStatus.BAD_REQUEST);
        }
    }


    public Optional<UserPrinciple> findOneWithAuthoritiesByUsername(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    public String findUsername(String username) {
        try {
            return userRepository.findUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public String findUserPassword(String username) {
        try {
            return userRepository.findUserPassword(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public String findUserEmail(String userEmail) {
        try {
            return userRepository.findUserEmail(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public void enableUser(String email) {
        try {
            userRepository.enableUser(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public boolean isUserExist(String userEmail) {
        try {
            return userRepository.isUserExist(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }
}
