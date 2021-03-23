package ru.geekbrains.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.exceptions.ProductNotFoundException;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.model.ProductDto;
import ru.geekbrains.boot.model.ProductMapper;
import ru.geekbrains.boot.repositories.ProductRepository;
import org.springframework.data.domain.Page;


import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Page<ProductDto> getAll(Specification<Product> spec, int page, int size, Sort sort) {
        return productRepository.findAll(spec, PageRequest.of(page, size, sort)).map(mapper::toDto);
    }

    public ProductDto getById(Long id) {
        Optional<ProductDto> product = productRepository.findById(id).map(mapper::toDto);
        return product.orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %d not found", id)));
    }

    public ProductDto add(ProductDto student) {
        return mapper.toDto(productRepository.save(mapper.toEntity(student)));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}