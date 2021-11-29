package com.school.core.response;

import com.school.core.entity.Response;
import org.springframework.http.HttpStatus;


public class AppResponse {
    public Response badRequest(String message) {
        return new Response(HttpStatus.BAD_REQUEST.value(), message);
    }

    public Response success(String message) {
        return new Response(HttpStatus.OK.value(), message);
    }


}
