package com.shopify.dto;

import com.shopify.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private String message;

    private String token;

    private Long user_id;

    private String username;

    private String email;

    private RoleEnum role;
}
