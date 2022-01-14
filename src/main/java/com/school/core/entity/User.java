package com.school.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String gender;
    private Date dob;
    private String avatar;
    private Integer role_id;
    private String status;
}
