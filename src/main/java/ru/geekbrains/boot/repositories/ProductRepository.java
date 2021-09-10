package ru.geekbrains.boot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.boot.model.Category;
import ru.geekbrains.boot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findProductByCostLessThan(float cost, Pageable pageable);

    Page<Product> findProductByCostGreaterThan(float cost, Pageable pageable);

    Page<Product> findProductByCostBetween(float minCost, float maxCost, Pageable pageable);

    Page<Product> findProductByTitleContainsIgnoreCase(String s, Pageable pageable);

    Page<Product> findProductByCategory(Category category, Pageable pageable);
}
