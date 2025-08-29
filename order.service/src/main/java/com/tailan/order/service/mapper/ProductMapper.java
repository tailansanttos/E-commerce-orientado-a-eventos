package com.tailan.order.service.mapper;

import com.tailan.order.service.database.model.Product;
import com.tailan.order.service.dtos.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}
