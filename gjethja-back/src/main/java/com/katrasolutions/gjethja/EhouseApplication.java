package com.katrasolutions.gjethja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EhouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhouseApplication.class, args);
    }

}
