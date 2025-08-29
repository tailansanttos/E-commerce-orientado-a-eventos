package com.tailan.order.service.service;

import com.tailan.order.service.database.model.Product;
import com.tailan.order.service.dtos.ProductRequest;
import com.tailan.order.service.dtos.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public ProductResponse createProduct(ProductRequest productRequest);
    public ProductResponse updateProduct(UUID productId, ProductRequest productRequest);
    public void deleteProduct(UUID productId);
    public ProductResponse getProductResponseDto(UUID productId);
    public Product getProductById(UUID productId);
    public List<ProductResponse> getAllProducts();
}
