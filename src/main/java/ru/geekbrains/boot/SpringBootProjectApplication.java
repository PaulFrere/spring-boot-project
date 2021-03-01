package ru.geekbrains.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.boot.services.PrepareDataApp;

@SpringBootApplication
public class SpringBootProjectApplication {
	public static void main(String[] args) {
		PrepareDataApp.forcePrepareData();
		SpringApplication.run(ru.geekbrains.boot.SpringBootProjectApplication.class, args);
	}
}
