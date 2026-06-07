package com.shopify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    private String store_name;

    private String description;

    private LocalDateTime created_at;

    // each store can have one seller (user) only
    // this is a foreign key (because we used JoinColumn)
    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    // one store can have many products
    @OneToMany(mappedBy = "store_id")
    private List<Product> products;
}