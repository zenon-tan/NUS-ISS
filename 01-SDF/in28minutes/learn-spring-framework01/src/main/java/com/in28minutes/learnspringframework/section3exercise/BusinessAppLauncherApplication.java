package com.in28minutes.learnspringframework.section3exercise;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan

public class BusinessAppLauncherApplication {

    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(BusinessAppLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);

            System.out.println(context.getBean(BusinessCalculationService.class).findMax());

        }
        
    }

    
}