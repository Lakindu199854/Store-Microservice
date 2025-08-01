package com.smartshop.product_service.repository;

import com.smartshop.product_service.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<ProductEntity,String> {
}
