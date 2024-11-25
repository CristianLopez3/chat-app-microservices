package com.cristian.msusersservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-messages-service")
public interface MessageService {

    @GetMapping("/messages/{userId}/conversations")
    List<Long> getConversationsByUserId(@PathVariable long userId);

}
