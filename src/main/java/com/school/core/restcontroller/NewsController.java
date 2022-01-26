package com.school.core.restcontroller;

import com.school.core.entity.Gallery;
import com.school.core.entity.News;
import com.school.core.entity.Pig;
import com.school.core.response.AppResponse;
import com.school.core.service.news.NewsService;
import com.school.core.service.tokenchecker.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/get-news")
    public ResponseEntity<?> getAllNews() {
        try {
            return newsService.getAllNews();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-news-by-news-code/{newscode}")
    public ResponseEntity<?> getNewsByNewsCode(@PathVariable("newscode") String newsCode) {
        try {
            return newsService.getNewsByNewsCode(newsCode);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get-news-by-filter")
    public ResponseEntity<?> getNewsByFilter(@RequestBody News newsFilter) {
        try {
            return newsService.getAllNewsByFilter(newsFilter);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count-news")
    public ResponseEntity<?> countNews() {
        try {
            return newsService.countNews();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppResponse().badRequest("Something's Wrong!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-news")
    public ResponseEntity<?> saveNews(@RequestHeader HttpHeaders httpHeaders, @RequestBody News news) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return newsService.saveNews(news);
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

    @PostMapping("/update-news")
    public ResponseEntity<?> updateNews(@RequestHeader HttpHeaders httpHeaders, @RequestBody News news) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return newsService.updateNews(news);
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

    @PostMapping("/delete-news")
    public ResponseEntity<?> deleteNews(@RequestHeader HttpHeaders httpHeaders, @RequestBody News news) {
        try {
            List<String> tokenList = httpHeaders.get("access_token");
            if (tokenList != null && tokenList.size() > 0) {
                String token = tokenList.get(0);
                if (token != null && !token.equals("") && token.length() > 0) {
                    boolean isTokenValid = jwtService.checkToken(token);
                    if (isTokenValid) {
                        return newsService.deleteNews(news);
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
