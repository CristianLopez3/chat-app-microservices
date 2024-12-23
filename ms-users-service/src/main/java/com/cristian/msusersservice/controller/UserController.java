package com.cristian.msusersservice.controller;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final  Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        logger.debug("Getting all users");
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        logger.debug("Creating user with data: {}", userRequestDto);
        var userResponse = userService.create(userRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("uuid") String uuid){
        logger.debug("Getting user with id: {}", uuid);
        return ResponseEntity.ok(userService.getByUUID(uuid));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByUuid(@RequestParam("uuid") String uuid){
        return ResponseEntity.ok(userService.existsByUuid(UUID.fromString(uuid)));
    }

}