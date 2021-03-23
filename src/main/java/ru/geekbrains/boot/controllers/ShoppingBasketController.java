package ru.geekbrains.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.ProductDto;
import ru.geekbrains.boot.services.ShoppingBasketService;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/basket")
public class ShoppingBasketController {

    private final ShoppingBasketService shoppingBasketService;

    @Autowired
    public ShoppingBasketController(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @GetMapping
    public ResponseEntity<Map<ProductDto, Integer>> getBasket() {
        return ResponseEntity.ok(shoppingBasketService.getProducts());
    }

    @PutMapping()
    public ResponseEntity<Map<ProductDto, Integer>> addProduct(@RequestParam Long productId) {
        shoppingBasketService.addProduct((long) Math.toIntExact(productId));
        return ResponseEntity.ok(shoppingBasketService.getProducts());
    }

    @DeleteMapping
    public ResponseEntity<Map<ProductDto, Integer>> removeProduct(@RequestParam Long productId) {
        shoppingBasketService.removeProduct((long) Math.toIntExact(productId));
        return ResponseEntity.ok(shoppingBasketService.getProducts());
    }
}