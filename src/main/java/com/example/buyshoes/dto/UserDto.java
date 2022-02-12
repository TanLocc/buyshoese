package com.example.buyshoes.dto;

import lombok.Data;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    Long id;
    String username;
    String password;
    String phone;
    String address;
    String email;
    String role;
    CommonsMultipartFile avatar;
}