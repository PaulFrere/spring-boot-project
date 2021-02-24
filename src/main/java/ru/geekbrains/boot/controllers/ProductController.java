package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "all_products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable String id, Model model) {
        Optional<Product> product = productService.get(Integer.parseInt(id));
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product";
        } else {
            model.addAttribute("product", id);
            return "product_not_found";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products/all";
    }
}
