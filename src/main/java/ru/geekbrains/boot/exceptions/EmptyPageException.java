package ru.geekbrains.boot.exceptions;

public class EmptyPageException extends RuntimeException {
    public EmptyPageException(String message) {
        super(message);
    }
}