package com.school.core.restcontroller;

import com.school.core.entity.RegistrationRequest;
import com.school.core.response.AppResponse;
import com.school.core.service.pig.PigService;
import com.school.core.service.tokenchecker.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
public class AppController extends BaseController {


    @Autowired
    private PigService pigService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
