package com.groupmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.groupmanagement"})
@EnableFeignClients
public class GroupManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupManagementApplication.class, args);
    }

}
