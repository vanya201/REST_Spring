package com.example.REST_Spring.controller;

import com.example.REST_Spring.dto.ProductDTO;
import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ProductDTO saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable long id) {
        return productService.findProductById(id);
    }

    @PutMapping("/product")
    public ProductDTO updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.delete(id);
    }
}
