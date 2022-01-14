package com.school.core.restcontroller;

import com.school.core.entity.Gallery;
import com.school.core.response.AppResponse;
import com.school.core.service.gallery.GalleryService;
import com.school.core.service.tokenchecker.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    @Autowired
    private JWTService jwtService;


    @GetMapping("/get-gallery")
    public ResponseEntity<?> getAllGallery() {
        try {
            return galleryService.getAllGallery();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-gallery")
    public ResponseEntity<?> saveGallery(@RequestHeader HttpHeaders httpHeaders, @RequestBody Gallery gallery) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    System.out.println("- Token Header: " + token);
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return galleryService.saveGallery(gallery);
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

    @PostMapping("/update-gallery")
    public ResponseEntity<?> updateGallery(@RequestHeader HttpHeaders httpHeaders, @RequestBody Gallery gallery) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    System.out.println("- Token Header: " + token);
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return galleryService.updateGallery(gallery);
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
