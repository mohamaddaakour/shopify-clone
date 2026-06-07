package com.shopify.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// @Email means this field should be in email format
@Data
public class LoginRequestDTO {
    @Email(message = "Invalid email")
    @NotBlank(message = "Price is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
