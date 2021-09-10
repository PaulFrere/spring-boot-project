package ru.geekbrains.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.exceptions.CategoryNotFoundException;
import ru.geekbrains.boot.exceptions.EmptyPageException;
import ru.geekbrains.boot.exceptions.ProductNotFoundException;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.model.ProductDto;
import ru.geekbrains.boot.repositories.CategoryRepository;
import ru.geekbrains.boot.repositories.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto getProduct(Long id) {
        return new ProductDto(productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException(String.format("Product with ID: %d not found", id))));
    }

    public List<ProductDto> getProductsBetweenCost(float minCost, float maxCost, Pageable pageable) {
        Page<ProductDto> productPage = productRepository.findProductByCostBetween(minCost, maxCost, pageable).map(ProductDto::new);
        if (productPage.getContent().size() == 0)
            throw new EmptyPageException(String.format("No products between %f and %f prices", minCost, maxCost));
        return productPage.getContent();
    }

    public List<ProductDto> getProductsCheaperThan(float cost, Pageable pageable) {
        Page<ProductDto> productPage = productRepository.findProductByCostLessThan(cost, pageable).map(ProductDto::new);
        if (productPage.getContent().size() == 0)
            throw new EmptyPageException(String.format("No products cheaper than %f DKK", cost));
        return productPage.getContent();
    }

    public List<ProductDto> getProductsExpensiveThan(float cost, Pageable pageable) {
        Page<ProductDto> productPage = productRepository.findProductByCostGreaterThan(cost, pageable).map(ProductDto::new);
        if (productPage.getContent().size() == 0)
            throw new EmptyPageException(String.format("No products more expensive than %f DKK", cost));
        return productPage.getContent();
    }

    public List<ProductDto> getProductByTitleContains(String s, Pageable pageable) {
        Page<ProductDto> productPage = productRepository.findProductByTitleContainsIgnoreCase(s, pageable).map(ProductDto::new);
        if (productPage.getContent().size() == 0)
            throw new EmptyPageException(String.format("No products with '%s' in title", s));
        return productPage.getContent();
    }

    public ProductDto addOrUpdateProduct(ProductDto productDto) {
        Product newProduct = new Product(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getCost(),
                categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException("Category doesn't exist"))
        );
        return new ProductDto(productRepository.save(newProduct));
    }

    public List<ProductDto> getAllProductsInCategory(int page, int size, Sort sort, Long categoryId) {
        Page<ProductDto> productPage = productRepository.findProductByCategory(
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CategoryNotFoundException("Category doesn't exist")),
                PageRequest.of(page, size, sort))
                .map(ProductDto::new);
        if (productPage.getContent().size() == 0)
            throw new EmptyPageException(String.format("Page number %d doesn't exist.", page));
        return productPage.getContent();
    }

    public List<ProductDto> getAllProducts(int page, int size, Sort sort) {
        Page<ProductDto> productPage = productRepository.findAll(PageRequest.of(page, size, sort)).map(ProductDto::new);
        if (productPage.getContent().size() == 0)
            throw new EmptyPageException(String.format("Page number %d doesn't exist.", page));
        return productPage.getContent();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(Long.valueOf(id));
    }

    public List<ProductDto> getById(Long id) {
        Page<ProductDto> productPage = productRepository.findProductByIdIgnoreCase(Math.toIntExact(id)).map(ProductDto::new);
        return productPage.getContent();
    }
}
