package com.example.denimshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DenimShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DenimShopApplication.class, args);
    }

}
