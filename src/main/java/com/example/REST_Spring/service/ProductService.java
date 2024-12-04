package com.example.REST_Spring.service;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.REST_Spring.dto.ProductDTO;
import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.model.Shop;
import com.example.REST_Spring.repository.ProductRepository;
import com.example.REST_Spring.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductDTO.mapToProductDTO(product);
    }

    public ProductDTO save(Product product) {
        Set<Shop> shopsFromDb = new HashSet<>();
        shopRepository.findAllById(
                product.getShops().stream().map(Shop::getId).collect(Collectors.toSet())
        ).forEach(shopsFromDb::add);

        product.setShops(shopsFromDb);
        shopsFromDb.forEach(shop -> shop.getProducts().add(product)); // добавляем продукт в магазин

        Product savedProduct = productRepository.save(product);
        shopRepository.saveAll(shopsFromDb);
        return ProductDTO.mapToProductDTO(savedProduct);
    }

    public ProductDTO update(Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setDescription(product.getDescription());
        existingProduct.setBrand(product.getBrand());

        Set<Shop> shopsFromDb = new HashSet<>();
        shopRepository.findAllById(
                product.getShops().stream().map(Shop::getId).collect(Collectors.toSet())
        ).forEach(shopsFromDb::add);

        shopsFromDb.forEach(shop -> shop.getProducts().add(existingProduct));

        existingProduct.setShops(shopsFromDb);
        Product updatedProduct = productRepository.save(existingProduct);
        shopRepository.saveAll(shopsFromDb);
        return ProductDTO.mapToProductDTO(updatedProduct);
    }

    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.getShops().forEach(shop -> shop.getProducts().remove(product));
        shopRepository.saveAll(product.getShops());

        productRepository.delete(product);
    }
}



