package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/cost_between")
    public List<Product> getAllBetween(@RequestParam float first, @RequestParam float second) {
        return productService.getAllByCostBetween(first, second);
    }

    @GetMapping("/cost_less")
    public List<Product> getAllLess(@RequestParam float cost) {
        return productService.getAllByCostIsLessThanEqual(cost);
    }

    @GetMapping("/cost_greater")
    public List<Product> getAllGreater(@RequestParam float cost) {
        return productService.getAllByCostGreaterThanEqual(cost);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }


    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
