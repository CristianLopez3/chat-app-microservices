package com.cristian.msgatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGatewayServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(MsGatewayServiceApplication.class);

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
								.rewritePath("/chat/(?<segment>.*)", "/api/${segment}")
								.addResponseHeader("X-response-time", LocalDateTime.now().toString())
						)
						.uri("lb://ms-users-service"))
				.route("chat-service", r -> r
						.path("/chat/messages/**")
						.filters(f -> f
								.rewritePath("/chat/messages/(?<segment>.*)", "/${segment}") // Elimina el prefijo si es necesario
								.addResponseHeader("X-response-time", LocalDateTime.now().toString()))
						.uri("lb://ms-chat-service")
				)

				.build();
	}

	@Bean
	public GlobalFilter loggingFilter() {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();

			logger.info("Request Headers: {} ", request.getHeaders());
			logger.info("Request Path: {}", request.getPath());
			logger.info("Request Method: {} ", request.getMethod());

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("Response Status Code: " + response.getStatusCode());
			}));
		};
	}


}
