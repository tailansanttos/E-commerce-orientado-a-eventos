package com.tailan.order.service.service.impl;

import com.tailan.order.service.database.model.Product;
import com.tailan.order.service.database.repository.ProductRepository;
import com.tailan.order.service.dtos.ProductRequest;
import com.tailan.order.service.dtos.ProductResponse;
import com.tailan.order.service.exceptions.ProductNotFoundException;
import com.tailan.order.service.mapper.ProductMapper;
import com.tailan.order.service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setQuantity(productRequest.quantity());
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(UUID productId, ProductRequest productRequest) {
        Product product = getProductById(productId);
        if (!productRequest.name().isEmpty()){
            product.setName(productRequest.name());
        }
        Product productAtualizado = productRepository.save(product);
        return productMapper.toProductResponse(productAtualizado);
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public ProductResponse getProductResponseDto(UUID productId) {
       Product product = getProductById(productId);
       return productMapper.toProductResponse(product);
    }

    @Override
    public Product getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));
        return product;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
