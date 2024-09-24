package com.example.productservice.repository;

import com.example.productservice.model.product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface productRepository extends MongoRepository<product,String> {
}
