package com.cristian.msusersservice.controller;

import com.cristian.msusersservice.dto.LoginRequestDto;
import com.cristian.msusersservice.dto.UserResponseDto;
import com.cristian.msusersservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto userRequestDto){
        logger.info("User init session: {}", userRequestDto);
        return ResponseEntity.ok(authService.login(userRequestDto));
    }

}
