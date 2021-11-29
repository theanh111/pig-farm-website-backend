package com.school.core.restcontroller;

import com.school.core.response.BaseException;
import com.school.core.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class BaseController {
    HttpStatus status;
    Map<String, Object> body;

    @Autowired
    private BaseResponse response;

    void success(Object data) {
        status = HttpStatus.OK;
        body = response.success(data);
    }

    void failed(BaseException e) {
        status = e.getErrorStatus();
        body = response.failed(e.getMessage());
    }

}
