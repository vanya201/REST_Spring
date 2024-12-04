package com.example.REST_Spring.service;

import com.example.REST_Spring.dto.BrandDTO;
import com.example.REST_Spring.model.Brand;
import com.example.REST_Spring.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandDTO findBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
        return BrandDTO.mapToBrandDTO(brand);
    }

    public BrandDTO save(Brand brand) {
        Brand brandPr = brandRepository.save(brand);
        return BrandDTO.mapToBrandDTO(brandPr);
    }

    public BrandDTO update(Brand brand) {
        Brand existingBrand = brandRepository.findById(brand.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        existingBrand.setName(brand.getName());
        existingBrand.setDescription(brand.getDescription());
        Brand updatedBrand = brandRepository.save(existingBrand);
        return BrandDTO.mapToBrandDTO(updatedBrand);
    }

    public void delete(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brandRepository.delete(brand);
    }


}
