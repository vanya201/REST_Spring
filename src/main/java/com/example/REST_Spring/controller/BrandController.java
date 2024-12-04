package com.example.REST_Spring.controller;

import com.example.REST_Spring.dto.BrandDTO;
import com.example.REST_Spring.model.Brand;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.REST_Spring.service.BrandService;

@RestController
public class BrandController {
    @Autowired
    BrandService brandService;

    @PostMapping("/brand")
    public BrandDTO saveBrand(@RequestBody Brand brand) {
        return brandService.save(brand);
    }

    @GetMapping("/brand/{id}")
    public BrandDTO getBrand(@PathVariable long id) {
        return brandService.findBrandById(id);
    }

    @PutMapping("/brand")
    public BrandDTO updateBrand(@RequestBody Brand brand) {
        return brandService.update(brand);
    }

    @DeleteMapping("/brand/{id}")
    public void deleteBrand(@PathVariable long id) {
        brandService.delete(id);
    }
}
