package com.cristian.msgatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class RouteLocatorConfiguration {

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
                                .rewritePath("/chat/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-response-time", LocalDateTime.now().toString())
                        )
                        .uri("lb://ms-users-service"))
                .route("messages-service", r -> r
                        .path("/chat/messages/**")
                        .filters(f -> f
                                .rewritePath("/chat/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-response-time", LocalDateTime.now().toString())
                        )
                        .uri("lb://ms-messages-service")
                )
                .route("chat-service-sockjs", r -> r
                        .path("/ws/**")
                        .uri("lb:ws://MS-CHAT-SERVICE")
                )
                .build();
    }

}
