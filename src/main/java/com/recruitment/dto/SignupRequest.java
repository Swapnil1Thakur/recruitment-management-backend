package com.recruitment.dto;

import com.recruitment.model.UserType;
import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String address;
    private String profileHeadline;
    private UserType userType;
}
