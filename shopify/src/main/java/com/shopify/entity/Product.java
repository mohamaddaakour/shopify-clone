package com.shopify.entity;

import com.shopify.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String product_name;

    private String description;

    private BigDecimal price;

    private Integer stock_quantity;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private LocalDateTime created_at;

    // many products can be in one store
    // this is a foreign key
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store_id;
}