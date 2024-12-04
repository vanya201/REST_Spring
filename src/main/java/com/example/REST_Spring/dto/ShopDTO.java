package com.example.REST_Spring.dto;

import com.example.REST_Spring.model.Brand;
import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.model.Shop;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ShopDTO {
    private Long id;
    private String description;
    private BrandDTO brand;
    private Set<ProductDTO> products = new HashSet<>();

    public static ShopDTO mapToShopDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setId(shop.getId());
        shopDTO.setDescription(shop.getDescription());

        Brand brand = shop.getBrand();
        if (brand != null) {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setId(brand.getId());
            brandDTO.setDescription(brand.getDescription());
            shopDTO.setBrand(brandDTO);
        }

        Set<ProductDTO> productDTOs = new HashSet<>();
        for (Product product : shop.getProducts()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setDescription(product.getDescription());
            productDTOs.add(productDTO);
        }
        shopDTO.setProducts(productDTOs);

        return shopDTO;
    }
}
