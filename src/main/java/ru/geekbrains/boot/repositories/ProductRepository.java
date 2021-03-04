package ru.geekbrains.boot.repositories;

import ru.geekbrains.boot.model.Product;

import java.util.List;

public interface ProductRepository {
    void create(String title, float cost);

    ru.geekbrains.boot.entities.Product get(int id);

    List<ru.geekbrains.boot.entities.Product> getAll();

    void update(int id, String title, float cost);

    void delete(int id);

    void save(Product product);

    void save(ru.geekbrains.boot.entities.Product product);
}
