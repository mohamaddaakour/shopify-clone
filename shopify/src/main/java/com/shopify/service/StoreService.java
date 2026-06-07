package com.shopify.service;

import com.shopify.dto.StoreRequestDTO;
import com.shopify.dto.StoreResponseDTO;
import com.shopify.entity.Store;
import com.shopify.entity.User;
import com.shopify.enums.RoleEnum;
import com.shopify.repository.StoreRepository;
import com.shopify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public StoreResponseDTO createStore(StoreRequestDTO request, User seller) {
        if (storeRepository.existsBySeller_UserId(seller.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already has a store");
        }

        Store store = Store.builder()
                .store_name(request.getStore_name())
                .description(request.getDescription())
                .seller(seller)
                .created_at(LocalDateTime.now())
                .build();

        if (seller.getRole() != RoleEnum.SELLER && seller.getRole() != RoleEnum.ADMIN) {
            seller.setRole(RoleEnum.SELLER);
            userRepository.save(seller);
        }

        return toResponse(storeRepository.save(store));
    }

    public List<StoreResponseDTO> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StoreResponseDTO getStore(Long storeId) {
        Store store = findStore(storeId);

        return toResponse(store);
    }

    public StoreResponseDTO getMyStore(User seller) {
        Store store = findStoreBySeller(seller.getUserId());

        return toResponse(store);
    }

    public StoreResponseDTO updateMyStore(StoreRequestDTO request, User seller) {
        Store store = findStoreBySeller(seller.getUserId());

        store.setStore_name(request.getStore_name());
        store.setDescription(request.getDescription());

        return toResponse(storeRepository.save(store));
    }

    public void deleteMyStore(User seller) {
        Store store = findStoreBySeller(seller.getUserId());

        storeRepository.delete(store);

        if (seller.getRole() == RoleEnum.SELLER) {
            seller.setRole(RoleEnum.CUSTOMER);
            userRepository.save(seller);
        }
    }

    private Store findStore(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
    }

    private Store findStoreBySeller(Long sellerId) {
        return storeRepository.findBySeller_UserId(sellerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
    }

    private StoreResponseDTO toResponse(Store store) {
        User seller = store.getSeller();

        return new StoreResponseDTO(
                store.getStore_id(),
                store.getStore_name(),
                store.getDescription(),
                seller.getUserId(),
                seller.getUsername(),
                store.getCreated_at()
        );
    }
}
