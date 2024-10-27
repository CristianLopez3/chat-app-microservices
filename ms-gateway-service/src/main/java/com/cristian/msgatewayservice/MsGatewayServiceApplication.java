package com.cristian.msgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MsGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayServiceApplication.class, args);
	}



	@Bean
	public RouteLocator chatRouterLocator(RouteLocatorBuilder routerLocatorBuilder) {
		return routerLocatorBuilder.routes()
				.route("users-service", r -> r
						.path("/chat/users/**") // Ruta en el Gateway. E.X: http://localhost:8089/chat/users
						.filters(f -> f
								/**
								 *  example: http://localhost:8089/chat/users/users -> where the second users is the segment
								 *  would redirect to
								 *  http://localhost:8080/api/v1/users
								 */
								.rewritePath("/chat/(?<segment>.*)", "/api/v1/${segment}")
								.addResponseHeader("X-response-time", LocalDateTime.now().toString())
						)
						.uri("lb://ms-users-service")
				)
				.build();
	}

}
