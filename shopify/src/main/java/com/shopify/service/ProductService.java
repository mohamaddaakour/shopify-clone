// package com.shopify.service;

// import com.shopify.dto.ProductRequestDTO;
// import com.shopify.dto.ProductResponseDTO;
// import com.shopify.entity.Product;
// import com.shopify.entity.Store;
// import com.shopify.entity.User;
// import com.shopify.repository.ProductRepository;
// import com.shopify.repository.StoreRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// @RequiredArgsConstructor
// public class ProductService {

//     private final ProductRepository productRepository;
//     private final StoreRepository storeRepository;

//     public ProductResponseDTO createProduct(ProductRequestDTO request, User seller) {
//         Store store = findStoreBySeller(seller.getUserId());

//         Product product = Product.builder()
//                 .product_name(request.getProduct_name())
//                 .description(request.getDescription())
//                 .price(request.getPrice())
//                 .stock_quantity(request.getStock_quantity())
//                 .category(request.getCategory())
//                 .store_id(store)
//                 .created_at(LocalDateTime.now())
//                 .build();

//         return toResponse(productRepository.save(product));
//     }

//     public List<ProductResponseDTO> getAllProducts() {
//         return productRepository.findAll()
//                 .stream()
//                 .map(this::toResponse)
//                 .toList();
//     }

//     public ProductResponseDTO getProduct(Long productId) {
//         Product product = findProduct(productId);

//         return toResponse(product);
//     }

//     public List<ProductResponseDTO> getProductsByStore(Long storeId) {
//         if (!storeRepository.existsById(storeId)) {
//             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found");
//         }

//         return productRepository.findByStoreId(storeId)
//                 .stream()
//                 .map(this::toResponse)
//                 .toList();
//     }

//     public List<ProductResponseDTO> getMyProducts(User seller) {
//         return productRepository.findBySellerId(seller.getUserId())
//                 .stream()
//                 .map(this::toResponse)
//                 .toList();
//     }

//     public ProductResponseDTO updateMyProduct(Long productId, ProductRequestDTO request, User seller) {
//         Product product = findProductOwnedBySeller(productId, seller.getUserId());

//         product.setProduct_name(request.getProduct_name());
//         product.setDescription(request.getDescription());
//         product.setPrice(request.getPrice());
//         product.setStock_quantity(request.getStock_quantity());
//         product.setCategory(request.getCategory());

//         return toResponse(productRepository.save(product));
//     }

//     public void deleteMyProduct(Long productId, User seller) {
//         Product product = findProductOwnedBySeller(productId, seller.getUserId());

//         productRepository.delete(product);
//     }

//     private Product findProduct(Long productId) {
//         return productRepository.findById(productId)
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
//     }

//     private Product findProductOwnedBySeller(Long productId, Long sellerId) {
//         return productRepository.findByIdAndSellerId(productId, sellerId)
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
//     }

//     private Store findStoreBySeller(Long sellerId) {
//         return storeRepository.findBySeller_UserId(sellerId)
//                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
//     }

//     private ProductResponseDTO toResponse(Product product) {
//         Store store = product.getStore_id();

//         return new ProductResponseDTO(
//                 product.getProduct_id(),
//                 product.getProduct_name(),
//                 product.getDescription(),
//                 product.getPrice(),
//                 product.getStock_quantity(),
//                 product.getCategory(),
//                 store.getStore_id(),
//                 store.getStore_name(),
//                 product.getCreated_at()
//         );
//     }
// }
