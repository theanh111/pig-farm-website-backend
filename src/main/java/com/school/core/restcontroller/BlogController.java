package com.school.core.restcontroller;

import com.school.core.entity.Blog;
import com.school.core.response.AppResponse;
import com.school.core.service.blog.BlogService;
import com.school.core.service.tokenchecker.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/get-blog")
    public ResponseEntity<?> getAllBlog() {
        try {
            return blogService.getAllBlog();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-blog-by-blog-code/{blogcode}")
    public ResponseEntity<?> getBlogByBlogCode(@PathVariable("blogcode") String blogCode) {
        try {
            return blogService.getBlogByBlogCode(blogCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-blog")
    public ResponseEntity<?> saveFarm(@RequestHeader HttpHeaders httpHeaders, @RequestBody Blog blog) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    System.out.println("- Token Header: " + token);
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return blogService.saveBlog(blog);
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
