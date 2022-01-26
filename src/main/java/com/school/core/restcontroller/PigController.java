package com.school.core.restcontroller;

import com.school.core.entity.*;
import com.school.core.response.AppResponse;
import com.school.core.service.pig.PigService;
import com.school.core.service.tokenchecker.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PigController extends BaseController {
    @Autowired
    private PigService pigService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/get-pig-by-filter")
    public ResponseEntity<?> getPigByFilter(@RequestBody Pig pigFilter) {
        try {
            return pigService.getAllPigByFilter(pigFilter);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-pig-by-farm-name")
    public ResponseEntity<?> getPigByFarmName(@RequestBody Pig pig) {
        try {
            return pigService.getPigByFarmName(pig.getDia_diem_danh_gia());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-pig-by-id/{id}")
    public ResponseEntity<?> getPigById(@PathVariable("id") Integer id) {
        try {
            return pigService.getPigById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count-pig")
    public ResponseEntity<?> countPig() {
        try {
            return pigService.countPig();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-pig")
    public ResponseEntity<?> getAllPig() {
        try {
            return pigService.getAllPig();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-pig-limit-12")
    public ResponseEntity<?> getPigLimit12() {
        try {
            return pigService.getPigLimit12();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-pig")
    public ResponseEntity<?> savePig(@RequestHeader HttpHeaders httpHeaders, @RequestBody Pig pig) {
        try {

            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return pigService.savePig(pig);
                    } else {
                        return new ResponseEntity<>(new AppResponse().badRequest("Token Invalid Or Expired!"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
            return new ResponseEntity<>(new AppResponse().badRequest("Please check Header Token!"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-pig-import")
    public ResponseEntity<?> savePigImportExcel(@RequestHeader HttpHeaders httpHeaders, @RequestBody DataRequest dataRequest) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return pigService.savePigImport(dataRequest);
                    } else {
                        return new ResponseEntity<>(new AppResponse().badRequest("Token Invalid Or Expired!"), HttpStatus.BAD_REQUEST);
                    }
                }
            }
            return new ResponseEntity<>(new AppResponse().badRequest("Please check Header Token!"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-pig")
    public ResponseEntity<?> updatePig(@RequestHeader HttpHeaders httpHeaders, @RequestBody Pig pig) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return pigService.updatePig(pig);
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

    @PostMapping("/update-pig-status")
    public ResponseEntity<?> updatePigStatus(@RequestHeader HttpHeaders httpHeaders, @RequestBody Pig pig) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return pigService.updatePigStatus(pig);
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

    @PostMapping("/delete-pig")
    public ResponseEntity<?> deletePig(@RequestHeader HttpHeaders httpHeaders, @RequestBody Pig pig) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return pigService.deletePig(pig);
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
