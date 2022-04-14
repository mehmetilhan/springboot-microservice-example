package com.todomanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.todomanagement"})
@EnableFeignClients
public class TodoManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoManagementApplication.class, args);
    }

}
