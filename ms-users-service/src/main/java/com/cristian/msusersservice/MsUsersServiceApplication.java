package com.cristian.msusersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsUsersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUsersServiceApplication.class, args);
    }

}
