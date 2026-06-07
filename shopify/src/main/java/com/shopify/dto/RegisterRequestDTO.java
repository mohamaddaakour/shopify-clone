package com.shopify.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;

    @Email(message = "Invalid email")
    @NotBlank(message = "Price is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
