package com.mjc.school.main;

import com.mjc.school.controller.ControllerCommands;
import com.mjc.school.main.config.AppConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy
@EntityScan(basePackages = {"com.mjc.school.repository.model"})
@EnableJpaAuditing
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ControllerCommands commands = context.getBean(ControllerCommands.class);
            commands.execute();
    }
}
