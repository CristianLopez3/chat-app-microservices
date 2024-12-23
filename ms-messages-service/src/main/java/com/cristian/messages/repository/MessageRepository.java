package com.cristian.messages.repository;

import com.cristian.messages.model.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    Flux<Message> findByConversationId(String conversationId);

}
