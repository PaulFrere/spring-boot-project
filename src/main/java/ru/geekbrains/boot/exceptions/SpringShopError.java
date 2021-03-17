package ru.geekbrains.boot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class SpringShopError {

    private int status;

    private String message;

    private LocalDateTime date;
}
