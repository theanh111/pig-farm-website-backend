package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmToken {
    private String token;
    private String created_at;
    private String expires_at;
    private String confirmed_at;
    private String user_email;
}
