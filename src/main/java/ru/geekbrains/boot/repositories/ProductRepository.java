package ru.geekbrains.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.boot.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByCostBetween(float first, float second);

    List<Product> findProductByCostIsLessThanEqual(float cost);

    List<Product> findProductsByCostGreaterThanEqual(float cost);
}
