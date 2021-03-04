package ru.geekbrains.boot.repositories;

import ru.geekbrains.boot.entities.Customer;

import java.util.List;

public interface CustomerRepository {
    void create(String name);

    Customer get(int id);

    List<Customer> getAll();

    void update(int id, String name);

    void delete(int id);

    void save(Customer customer);
}
