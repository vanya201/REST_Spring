package com.example.REST_Spring.repository;

import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.model.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    Shop findShopById(Long id);
}