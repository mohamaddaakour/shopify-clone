package com.shopify.dto;

import com.shopify.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

    private Long product_id;

    private String product_name;

    private String description;

    private BigDecimal price;

    private Integer stock_quantity;

    private CategoryEnum category;

    private Long store_id;

    private String store_name;

    private LocalDateTime created_at;
}
