package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping

    public Page<Product> getAllProducts(@PageableDefault(size = 10, page = 0, sort = "cost") Pageable pageable) {
        return productService.getAllProducts(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
    }

    @GetMapping(params = {"minCost", "maxCost"})
    public Page<Product> getProductsBetweenCost(float minCost, float maxCost, Pageable pageable) {
        return productService.getProductsBetweenCost(minCost, maxCost, pageable);
    }

    @GetMapping(params = "titleContains")
    public Page<Product> getProductByTitleContains(String titleContains, Pageable pageable) {
        return productService.getProductByTitleContains(titleContains, pageable);
    }

    @GetMapping(params = "minCost")
    public Page<Product> getProductsMoreExpensiveThan(float minCost, Pageable pageable) {
        return productService.getProductsExpensiveThan(minCost, pageable);
    }

    @GetMapping(params = "maxCost")
    public Page<Product> getProductsCheaperThan(float maxCost, Pageable pageable) {
        return productService.getProductsCheaperThan(maxCost, pageable);
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
}
