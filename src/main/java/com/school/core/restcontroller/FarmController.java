package com.school.core.restcontroller;

import com.school.core.entity.*;
import com.school.core.response.AppResponse;
import com.school.core.service.farm.FarmService;
import com.school.core.service.tokenchecker.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FarmController {
    @Autowired
    private FarmService farmService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/get-farm-by-id/{id}")
    public ResponseEntity<?> getFarmById(@PathVariable("id") Integer id) {
        try {
            return farmService.getFarmById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-farm-by-farm-code/{farmcode}")
    public ResponseEntity<?> getFarmByFarmCode(@PathVariable("farmcode") String farmCode) {
        try {
            return farmService.getFarmByFarmCode(farmCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-farm")
    public ResponseEntity<?> getAllFarm() {
        try {
            return farmService.getAllFarm();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-farm-by-filter")
    public ResponseEntity<?> getFarmByFilter(@RequestBody Farm farmFilter) {
        try {
            return farmService.getAllFarmByFilter(farmFilter);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count-farm")
    public ResponseEntity<?> countFarm() {
        try {
            return farmService.countFarm();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-farm")
    public ResponseEntity<?> saveFarm(@RequestHeader HttpHeaders httpHeaders, @RequestBody Farm farm) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return farmService.saveFarm(farm);
                    } else {
                        return new ResponseEntity<>(new AppResponse().badRequest("Token Invalid Or Expired!"), HttpStatus.UNAUTHORIZED);
                    }
                }
            }
            return new ResponseEntity<>(new AppResponse().badRequest("Please check Header Token!"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-farm")
    public ResponseEntity<?> updateFarm(@RequestHeader HttpHeaders httpHeaders, @RequestBody Farm farm) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return farmService.updateFarm(farm);
                    } else {
                        return new ResponseEntity<>(new AppResponse().badRequest("Token Invalid Or Expired!"), HttpStatus.UNAUTHORIZED);
                    }
                }
            }
            return new ResponseEntity<>(new AppResponse().badRequest("Please check Header Token!"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-farm")
    public ResponseEntity<?> deleteFarm(@RequestHeader HttpHeaders httpHeaders, @RequestBody Farm farm) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return farmService.deleteFarm(farm);
                    } else {
                        return new ResponseEntity<>(new AppResponse().badRequest("Token Invalid Or Expired!"), HttpStatus.UNAUTHORIZED);
                    }
                }
            }
            return new ResponseEntity<>(new AppResponse().badRequest("Please check Header Token!"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }




}
