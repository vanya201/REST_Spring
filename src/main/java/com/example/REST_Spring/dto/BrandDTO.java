package com.example.REST_Spring.dto;

import com.example.REST_Spring.model.Brand;
import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.model.Shop;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class BrandDTO {
    private Long id;
    private String name;
    private String description;
    private Set<ProductDTO> products = new HashSet<>();
    private Set<ShopDTO> shops = new HashSet<>();

    public static BrandDTO mapToBrandDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setDescription(brand.getDescription());

        Set<ProductDTO> productDTOs = new HashSet<>();
        for (Product product : brand.getProducts()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setDescription(product.getDescription());
            productDTO.setBrand(null);
            productDTOs.add(productDTO);
        }

        Set<ShopDTO> shopDTOs = new HashSet<>();
        for (Shop shop : brand.getShops()) {
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.setId(shop.getId());
            shopDTO.setDescription(shop.getDescription());
            shopDTO.setBrand(null);
            shopDTOs.add(shopDTO);
        }

        brandDTO.setProducts(productDTOs);
        brandDTO.setShops(shopDTOs);

        return brandDTO;
    }
}
