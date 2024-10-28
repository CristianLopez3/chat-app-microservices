package com.cristian.msusersservice.controller;

import com.cristian.msusersservice.dto.UserRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
        var userResponse = userService.create(userRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }



}
