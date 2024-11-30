package com.cristian.msconversationsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsConversationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConversationsServiceApplication.class, args);
	}

}
