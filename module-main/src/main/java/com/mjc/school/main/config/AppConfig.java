package com.mjc.school.main.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@Configuration
@ComponentScan("com.mjc.school.*")
@EntityScan(basePackages = {"com.mjc.school.repository.model"})
@EnableAspectJAutoProxy
public class AppConfig {

}
