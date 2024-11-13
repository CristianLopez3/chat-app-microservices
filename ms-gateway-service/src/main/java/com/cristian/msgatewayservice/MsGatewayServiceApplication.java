package com.cristian.msgatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class MsGatewayServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(MsGatewayServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MsGatewayServiceApplication.class, args);
    }

    @Bean
    public GlobalFilter loggingFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            logger.info("Request Headers: {} ", request.getHeaders());
            logger.info("Request Path: {}", request.getPath());
            logger.info("Request Method: {} ", request.getMethod());

            return chain.filter(exchange).then(Mono.fromRunnable(() ->
                    logger.info("Response Status Code: {}", response.getStatusCode())
            ));
        };
    }


}
