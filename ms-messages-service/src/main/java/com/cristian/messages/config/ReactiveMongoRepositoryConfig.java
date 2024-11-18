package com.cristian.messages.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReactiveMongoRepositoryConfig {

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create();
    }

}
