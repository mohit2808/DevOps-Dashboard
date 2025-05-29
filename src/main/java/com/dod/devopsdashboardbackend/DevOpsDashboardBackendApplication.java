package com.dod.devopsdashboardbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DevOpsDashboardBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevOpsDashboardBackendApplication.class, args);
    }
}
