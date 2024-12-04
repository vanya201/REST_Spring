package com.example.REST_Spring.repository;

import com.example.REST_Spring.model.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findBrandById(Long id);
}