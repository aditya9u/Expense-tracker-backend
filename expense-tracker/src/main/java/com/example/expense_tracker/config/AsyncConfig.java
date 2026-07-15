package com.example.expense_tracker.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {

   public AsyncConfig() {
        System.out.println("AsyncConfig Loaded");
    }


}