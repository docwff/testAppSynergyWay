package com.api.testappsynergyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TestAppSynergyWayApplication {


    public static void main(String[] args) {
        SpringApplication.run(TestAppSynergyWayApplication.class, args);

    }

    @PostConstruct
    public void initDB() {

    }
}
