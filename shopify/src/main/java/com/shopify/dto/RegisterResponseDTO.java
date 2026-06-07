package com.shopify.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.shopify.enums.RoleEnum;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegisterResponseDTO {
    @NotBlank(message = "username is required")
    private String username;

    @Email(message = "Invalid email")
    private String email;

    private RoleEnum role;

    private LocalDateTime created_at;
}
