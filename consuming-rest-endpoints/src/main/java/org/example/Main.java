package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { "org.example", "org.example.models", "org.example.clients", "org.example.controllers",
        "org.example.converters" })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
