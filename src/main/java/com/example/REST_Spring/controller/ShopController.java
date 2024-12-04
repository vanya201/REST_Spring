package com.example.REST_Spring.controller;

import com.example.REST_Spring.dto.ShopDTO;
import com.example.REST_Spring.model.Shop;
import com.example.REST_Spring.model.Shop;
import com.example.REST_Spring.service.ShopService;
import com.example.REST_Spring.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/shop")
    public ShopDTO saveShop(@RequestBody Shop Shop) {
        return shopService.save(Shop);
    }

    @GetMapping("/shop/{id}")
    public ShopDTO getShop(@PathVariable long id) {
        return shopService.findShopById(id);
    }

    @PutMapping("/shop")
    public ShopDTO updateShop(@RequestBody Shop Shop) {
        return shopService.update(Shop);
    }

    @DeleteMapping("/shop/{id}")
    public void deleteShop(@PathVariable long id) {
        shopService.delete(id);
    }
}
