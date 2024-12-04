package com.example.REST_Spring.service;

import com.example.REST_Spring.dto.ShopDTO;
import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.model.Shop;
import com.example.REST_Spring.repository.ProductRepository;
import com.example.REST_Spring.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository, ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    public ShopDTO findShopById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        return ShopDTO.mapToShopDTO(shop);
    }


    public ShopDTO save(Shop shop) {

        Set<Product> productsFromDb = new HashSet<>();
        productRepository.findAllById(
                shop.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
        ).forEach(productsFromDb::add);

        shop.setProducts(productsFromDb);
        productsFromDb.forEach(product -> product.getShops().add(shop));
        Shop savedShop = shopRepository.save(shop);
        productRepository.saveAll(productsFromDb);

        return ShopDTO.mapToShopDTO(savedShop);
    }


    public ShopDTO update(Shop shop) {
        Shop existingShop = shopRepository.findById(shop.getId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        existingShop.setDescription(shop.getDescription());
        existingShop.setBrand(shop.getBrand());

        Set<Product> productsFromDb = new HashSet<>();
        productRepository.findAllById(
                shop.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
        ).forEach(productsFromDb::add);

        productsFromDb.forEach(product -> product.getShops().add(existingShop));

        existingShop.setProducts(productsFromDb);
        Shop updatedShop = shopRepository.save(existingShop);
        productRepository.saveAll(productsFromDb);
        return ShopDTO.mapToShopDTO(updatedShop);
    }

    public void delete(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        shop.getProducts().forEach(product -> product.getShops().remove(shop));

        productRepository.saveAll(shop.getProducts());

        shopRepository.delete(shop);
    }
}
