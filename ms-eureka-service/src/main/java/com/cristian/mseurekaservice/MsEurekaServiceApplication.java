package com.cristian.mseurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEurekaServiceApplication.class, args);
    }


}
