package com.cristian.msgatewayservice.config;

import com.cristian.msgatewayservice.utils.RouterConstants;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                                .rewritePath(RouterConstants.ACTUAL_SEGMENT, RouterConstants.REPLACEMENT_SEGMENT)
                                .addResponseHeader(RouterConstants.RESPONSE_TIME_HEADER, LocalDateTime.now().toString())
                        )
                        .uri(RouterConstants.USERS_SERVICE))
                .route("conversations-service", r -> r
                        .path("/chat/conversations/**")
                        .filters(f -> f
                                .rewritePath(RouterConstants.ACTUAL_SEGMENT, RouterConstants.REPLACEMENT_SEGMENT)
                                .addResponseHeader("X-response-time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        )
                        .uri(RouterConstants.CONVERSATIONS_SERVICE)
                )
                .route("messages-service", r -> r
                        .path("/chat/messages/**")
                        .filters(f -> f
                                .rewritePath(RouterConstants.ACTUAL_SEGMENT, RouterConstants.REPLACEMENT_SEGMENT)
                                .addResponseHeader(RouterConstants.RESPONSE_TIME_HEADER, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                        )
                        .uri(RouterConstants.MESSAGES_SERVICE)
                )
                .route("chat-service-sockjs", r -> r
                        .path("/ws/**")
                        .uri(RouterConstants.CHAT_SERVICE)
                )
                .build();
    }

}
