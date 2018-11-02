package com.example.wordart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class WordartApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordartApiApplication.class, args);
    }
}
