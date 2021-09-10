package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.exceptions.ProductNotFoundException;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.model.ProductDto;
import ru.geekbrains.boot.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@RestController
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping

    public List<ProductDto> getAllProducts(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable) {
        return productService.getAllProducts(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
    }

    @GetMapping(params = "category")
    public List<ProductDto> getAllProductsInCategory(@PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable, Long category) {
        return productService.getAllProductsInCategory(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort(), category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addOrUpdateProduct(productDto);
    }

    @GetMapping(params = {"minCost", "maxCost"})
    public List<ProductDto> getProductsBetweenCost(float minCost, float maxCost, Pageable pageable) {
        return productService.getProductsBetweenCost(minCost, maxCost, pageable);
    }

    @GetMapping(params = "titleContains")
    public List<ProductDto> getProductByTitleContains(String titleContains, Pageable pageable) {
        return productService.getProductByTitleContains(titleContains, pageable);
    }

    @GetMapping(params = "minCost")
    public List<ProductDto> getProductsMoreExpensiveThan(float minCost, Pageable pageable) {
        return productService.getProductsExpensiveThan(minCost, pageable);
    }

    @GetMapping(params = "maxCost")
    public List<ProductDto> getProductsCheaperThan(float maxCost, Pageable pageable) {
        return productService.getProductsCheaperThan(maxCost, pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getOneProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.addOrUpdateProduct(productDto);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
    }
}
