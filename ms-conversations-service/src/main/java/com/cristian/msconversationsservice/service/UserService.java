package com.cristian.msconversationsservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient("ms-users-service")
public interface UserService {

    @GetMapping("/users/exists")
    boolean existsByUuid(@RequestParam("uuid") UUID uuid);

}
