package ru.geekbrains.boot.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String title;
    private float cost;
}
