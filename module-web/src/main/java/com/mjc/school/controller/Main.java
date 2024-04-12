package com.mjc.school.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mjc.school.repository",
        "com.mjc.school.service",
        "com.mjc.school.controller",
        "com.mjc.school"})
@EntityScan(basePackages = {"com.mjc.school.repository"})
@EnableWebMvc
@EnableJpaAuditing
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }
}
