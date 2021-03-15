package ru.geekbrains.boot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
@NoArgsConstructor
public class ProductDto {

    @NonNull
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private float cost;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoryId;

    private String categoryName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
    }
}
