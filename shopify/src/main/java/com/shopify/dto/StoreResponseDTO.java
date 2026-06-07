package com.shopify.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreResponseDTO {

    private Long store_id;

    private String store_name;

    private String description;

    private Long seller_id;

    private String seller_username;

    private LocalDateTime created_at;
}
