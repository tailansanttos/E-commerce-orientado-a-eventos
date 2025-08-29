package com.tailan.order.service.controller;

import com.tailan.order.service.dtos.ProductRequest;
import com.tailan.order.service.dtos.ProductResponse;
import com.tailan.order.service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/add")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") UUID productId) {
        ProductResponse product = productService.getProductResponseDto(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponse>  updateProduct(@PathVariable("productId") UUID productId, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productid") UUID productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
