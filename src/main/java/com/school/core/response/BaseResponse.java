package com.school.core.response;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {
    private Map<String, Object> body;

    public BaseResponse() {
        body = new HashMap<>();
    }

    public Map<String, Object> success(Object data) {
        body.clear();
        body.put("data", data);
        return body;
    }

    public Map<String, Object> failed(String message) {
        body.clear();
        body.put("message", message);
        return body;
    }
}
