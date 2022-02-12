package com.example.buyshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan("com.example.buyshoes.entities")
@EnableJpaRepositories("com.example.buyshoes.repository")
@SpringBootApplication
@EnableTransactionManagement
public class BuyShoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyShoesApplication.class, args);
    }

}
