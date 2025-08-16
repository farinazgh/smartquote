package com.example.smartquote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartQuoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartQuoteApplication.class, args);
    }
}