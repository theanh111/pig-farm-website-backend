package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private Integer code;
    private String message;
    private List<?> data;
}
