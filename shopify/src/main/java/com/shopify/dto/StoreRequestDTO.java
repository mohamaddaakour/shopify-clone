package com.shopify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreRequestDTO {

    @NotBlank(message = "Store name is required")
    private String store_name;

    private String description;
}
