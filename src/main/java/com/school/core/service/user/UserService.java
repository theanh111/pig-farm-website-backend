package com.school.core.service.user;

import com.school.core.entity.User;
import com.school.core.entity.UserPrinciple;
import com.school.core.repository.UserRepository;
import com.school.core.response.AppResponse;
import com.school.core.response.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> save(User user) {
        List<String> data = new ArrayList<>();
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

    public String findUsernameByUsername(String username) {
        try {
            return userRepository.findUsernameByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public ResponseEntity<?> findUserByUsername(String username) {
        try {
            List<User> data = new ArrayList<>();
            User user = userRepository.findUserByUsername(username);
            if (user != null) {
                if (user.getUsername() != null
                        && user.getFirst_name() != null
                        && user.getLast_name() != null
                        && user.getAvatar() != null
                ) {
                    data.add(user);
                    return new ResponseEntity<>(new AppResponse().successWithData("Find User By Username Successfully!", data), HttpStatus.OK);
                }

            }
            return new ResponseEntity<>(new AppResponse().badRequest("Can't find user with this username: " + username), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public String findUserPasswordByUsername(String username) {
        try {
            return userRepository.findUserPasswordByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public String findUserEmailByEmail(String userEmail) {
        try {
            return userRepository.findUserEmailByEmail(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }

    public String findUserEmailByUsername(String userName) {
        try {
            return userRepository.findUserEmailByUsername(userName);
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

    public boolean isUserConfirmEmail(String userEmail) {
        try {
            return userRepository.isUserConfirmEmail(userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.BAD_REQUEST, "Something's Wrong!");
        }
    }
}
