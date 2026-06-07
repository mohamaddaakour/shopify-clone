package com.shopify.repository;

import com.shopify.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findBySeller_UserId(Long sellerId);

    boolean existsBySeller_UserId(Long sellerId);
}
