package ru.geekbrains.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.repositories.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(int page, int size, Sort sort) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size, sort));
        if(productPage.getContent().size() > 0){
        return productPage;
        } else{
            throw new RuntimeException("No such page exists");
        }
    }

    public Page<Product> getProductsBetweenCost(float minCost, float maxCost, Pageable pageable) {
        Page<Product> productPage = productRepository.findProductByCostBetween(minCost, maxCost, pageable);
        if (productPage.getContent().size() > 0) {
            return productPage;
        } else {
            throw new RuntimeException("No such product");
        }
    }

    public Page<Product> getProductsCheaperThan(float cost, Pageable pageable) {
        Page<Product> productPage = productRepository.findProductByCostLessThan(cost, pageable);
        if (productPage.getContent().size() > 0) {
            return productPage;
        } else {
            throw new RuntimeException("No such product");
        }
    }

    public Page<Product> getProductsExpensiveThan(float cost, Pageable pageable) {
        Page<Product> productPage = productRepository.findProductByCostGreaterThan(cost, pageable);
        if (productPage.getContent().size() > 0) {
            return productPage;
        } else {
            throw new RuntimeException("No such product");
        }
    }

    public Page<Product> getProductByTitleContains(String s, Pageable pageable) {
        Page<Product> productPage = productRepository.findProductByTitleContainsIgnoreCase(s, pageable);
        if (productPage.getContent().size() > 0) {
            return productPage;
        } else {
            throw new RuntimeException("No such product");
        }
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
