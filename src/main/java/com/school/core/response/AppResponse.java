package com.school.core.response;

import com.school.core.entity.Response;
import com.school.core.entity.ResponseData;
import com.school.core.entity.ResponseObject;
import org.springframework.http.HttpStatus;

import java.util.List;


public class AppResponse {
    public Response badRequest(String message) {
        return new Response(HttpStatus.BAD_REQUEST.value(), message);
    }

    public Response success(String message) {
        return new Response(HttpStatus.OK.value(), message);
    }



    public ResponseData successWithData(String message, List<?> data) {
        return new ResponseData(HttpStatus.OK.value(), message, data);
    }

    public ResponseObject successWithObject(String message, Object object) {
        return new ResponseObject(HttpStatus.OK.value(), message, object);
    }

}
