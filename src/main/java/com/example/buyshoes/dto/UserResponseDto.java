package com.example.buyshoes.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseDto implements Serializable {

    Long id;
    String username;
    String password;
    String phone;
    String address;
    String email;
    String role;
}
