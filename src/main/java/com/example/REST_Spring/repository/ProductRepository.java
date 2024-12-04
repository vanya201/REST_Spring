package com.example.REST_Spring.repository;

import com.example.REST_Spring.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findProductById(Long id);
}