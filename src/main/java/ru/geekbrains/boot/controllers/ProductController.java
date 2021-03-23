package ru.geekbrains.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.exceptions.ProductNotFoundException;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.model.ProductDto;
import ru.geekbrains.boot.model.SortProduct;
import ru.geekbrains.boot.repositories.ProductSpecifications;
import ru.geekbrains.boot.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(@RequestParam MultiValueMap<String, String> params,
                                                   @RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "ASC") SortProduct costSortDirection,
                                                   @RequestParam(defaultValue = "ASC") SortProduct titleSortDirection,
                                                   @RequestParam(defaultValue = "TITLE") SortProduct mainSort
    ) {

        if (page <= 1) {
            page = 0;
        } else {
            page--;
        }

        Sort.Order costSorting = new Sort.Order(Sort.Direction.valueOf(costSortDirection.name()), "cost");
        Sort.Order titleSorting = new Sort.Order(Sort.Direction.valueOf(titleSortDirection.name()), "title");

        Sort resultSort;
        if (mainSort == SortProduct.COST) {
            resultSort = Sort.by(costSorting, titleSorting);
        } else {
            resultSort = Sort.by(titleSorting, costSorting);
        }

        Specification<Product> spec = ProductSpecifications.build(params);

        Page<ProductDto> products = productService.getAll(spec, page, size, resultSort);

        if (products.getTotalPages() <= page) {
            throw new ProductNotFoundException("Maximum page is " + products.getTotalPages());
        }
        return new ResponseEntity<>(products.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDto add(@RequestBody ProductDto product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}

