package com.shopify.controller;

import com.shopify.dto.StoreRequestDTO;
import com.shopify.dto.StoreResponseDTO;
import com.shopify.entity.User;
import com.shopify.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // @ResponseStatus(HttpStatus.CREATED) means when this method succeeds
    // return HTTP status code 201

    // @AuthenticationPrincipal means give me the currently authenticated user (loged in)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreResponseDTO createStore(@Valid @RequestBody StoreRequestDTO request, @AuthenticationPrincipal User user) {
        return storeService.createStore(request, user);
    }

    @GetMapping
    public List<StoreResponseDTO> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{storeId}")
    public StoreResponseDTO getStore(@PathVariable Long storeId) {
        return storeService.getStore(storeId);
    }

    @GetMapping("/me")
    public StoreResponseDTO getMyStore(@AuthenticationPrincipal User user) {
        return storeService.getMyStore(user);
    }

    @PutMapping("/me")
    public StoreResponseDTO updateMyStore(@Valid @RequestBody StoreRequestDTO request, @AuthenticationPrincipal User user) {
        return storeService.updateMyStore(request, user);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMyStore(@AuthenticationPrincipal User user) {
        storeService.deleteMyStore(user);
    }
}
