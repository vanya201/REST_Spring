package com.example.REST_Spring.dto;

import com.example.REST_Spring.model.Brand;
import com.example.REST_Spring.model.Product;
import com.example.REST_Spring.model.Shop;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String description;
    private BrandDTO brand;
    private Set<ShopDTO> shops = new HashSet<>();

    public static ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());

        Set<ShopDTO> shopDTOs = new HashSet<>();
        for (Shop shop : product.getShops()) {
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.setId(shop.getId());
            shopDTO.setDescription(shop.getDescription());
            shopDTOs.add(shopDTO);
        }

        Brand brand = product.getBrand();
        if (brand != null) {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setId(brand.getId());
            brandDTO.setDescription(brand.getDescription());
            productDTO.setBrand(brandDTO);
        }

        productDTO.setShops(shopDTOs);

        return productDTO;
    }
}
