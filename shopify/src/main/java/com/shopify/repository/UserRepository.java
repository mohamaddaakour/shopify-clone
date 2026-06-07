package com.shopify.repository;

import com.shopify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// we extends from JPA to use ORM methods
// User is the entity name and Long is the primary key data type
public interface UserRepository extends JpaRepository<User, Long> {

    // here we create a custom method, spring will do the implementation
    // automatically by the name of the method
    // Optional<User> means this method maybe will return nothing
    // if we give it a wrong email and can return the User data by its email
    Optional<User> findByEmail(String email);
}