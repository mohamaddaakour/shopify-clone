package com.shopify.dto;

import com.shopify.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentUserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private RoleEnum role;
}
