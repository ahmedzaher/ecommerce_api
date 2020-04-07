package com.ecommerce.ecommerce_api.dto;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
