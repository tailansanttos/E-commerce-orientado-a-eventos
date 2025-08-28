package com.tailan.order.service.database.repository;

import com.tailan.order.service.database.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
}
