package com.nzangi.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product Controller
 */

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;
    // Create Product Controller
    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    )
    {
     return ResponseEntity.ok(productService.createProduct(request));
    }

    // Purchase Products Controller
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
        @RequestBody List<ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    // Find Product By Id
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    // Find All Products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

}
