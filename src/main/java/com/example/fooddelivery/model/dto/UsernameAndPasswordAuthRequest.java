package com.example.fooddelivery.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameAndPasswordAuthRequest {

    private String email;
    private String password;
}