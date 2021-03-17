package ru.geekbrains.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.ProductDto;
import ru.geekbrains.boot.services.ShoppingBasketService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/basket")
public class ShoppingBasketController {

    @Autowired
    private ShoppingBasketService shoppingBasketService;

    @GetMapping
    public ResponseEntity<Map<List<ProductDto>, Integer>> getBasket() {
        return ResponseEntity.ok(shoppingBasketService.getProducts());
    }

    @PutMapping()
    public ResponseEntity<Map<List<ProductDto>, Integer>> addProduct(@RequestParam Long id) {
        shoppingBasketService.addProduct((long) Math.toIntExact(id));
        return ResponseEntity.ok(shoppingBasketService.getProducts());
    }

    @DeleteMapping
    public ResponseEntity<Map<List<ProductDto>, Integer>> removeProduct(@RequestParam Long id) {
        shoppingBasketService.deleteProduct((long) Math.toIntExact(id));
        return ResponseEntity.ok(shoppingBasketService.getProducts());
    }


}
