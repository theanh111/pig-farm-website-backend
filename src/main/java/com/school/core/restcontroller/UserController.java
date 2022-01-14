package com.school.core.restcontroller;

import com.school.core.entity.LoginRequest;
import com.school.core.entity.RegistrationRequest;
import com.school.core.entity.Token;
import com.school.core.entity.User;
import com.school.core.response.AppResponse;
import com.school.core.service.signin.LoginService;
import com.school.core.service.signin.RegisterService;
import com.school.core.service.tokenchecker.JWTService;
import com.school.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController extends BaseController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;


    @Autowired
    private LoginService loginService;

    @PostMapping("/check-token")
    public ResponseEntity<?> checkTokenJWT(@RequestBody String token) {
        try {
            boolean isTokenValid = jwtService.checkToken(token);
            if (isTokenValid) {
                return new ResponseEntity<>(new AppResponse().success("This user passed!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new AppResponse().badRequest("This user failed!"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/get-user")
    public ResponseEntity<?> getUserByUsername(@RequestBody User user) {
        try {
            return userService.findUserByUsername(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            return registerService.register(registrationRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return loginService.login(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        try {
            return registerService.confirmToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something's Wrong!", HttpStatus.BAD_REQUEST);
        }
    }
}
