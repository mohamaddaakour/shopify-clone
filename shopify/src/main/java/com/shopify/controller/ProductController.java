// package com.shopify.controller;

// import com.shopify.dto.ProductRequestDTO;
// import com.shopify.dto.ProductResponseDTO;
// import com.shopify.entity.User;
// import com.shopify.service.ProductService;
// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

// @RestController
// @RequestMapping("/products")
// @RequiredArgsConstructor
// public class ProductController {

//     private final ProductService productService;

//     @PostMapping
//     @ResponseStatus(HttpStatus.CREATED)
//     public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO request, @AuthenticationPrincipal User user) {
//         return productService.createProduct(request, user);
//     }

//     @GetMapping
//     public List<ProductResponseDTO> getAllProducts() {
//         return productService.getAllProducts();
//     }

//     @GetMapping("/{productId}")
//     public ProductResponseDTO getProduct(@PathVariable Long productId) {
//         return productService.getProduct(productId);
//     }

//     @GetMapping("/store/{storeId}")
//     public List<ProductResponseDTO> getProductsByStore(@PathVariable Long storeId) {
//         return productService.getProductsByStore(storeId);
//     }

//     @GetMapping("/me")
//     public List<ProductResponseDTO> getMyProducts(@AuthenticationPrincipal User user) {
//         return productService.getMyProducts(user);
//     }

//     @PutMapping("/{productId}")
//     public ProductResponseDTO updateMyProduct(@PathVariable Long productId, @Valid @RequestBody ProductRequestDTO request, @AuthenticationPrincipal User user) {
//         return productService.updateMyProduct(productId, request, user);
//     }

//     @DeleteMapping("/{productId}")
//     @ResponseStatus(HttpStatus.NO_CONTENT)
//     public void deleteMyProduct(@PathVariable Long productId, @AuthenticationPrincipal User user) {
//         productService.deleteMyProduct(productId, user);
//     }
// }
