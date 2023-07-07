package com.example.websitesecondhand.controller;

import com.example.websitesecondhand.dto.LoginReqDto;
import com.example.websitesecondhand.dto.MessageResponse;
import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "User authorization")
    @ApiResponse(responseCode = "200", description = "OK. User is authorized")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginReqDto loginReqDto) {
        log.info("Authority user: {}", loginReqDto.getUsername());
        return ResponseEntity.ok(authService.authenticateUser(loginReqDto));
    }

    @Operation(summary = "Register new user")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReqDto registerReqDto) {
        log.info("Register new user: {}", registerReqDto.getUsername());
        return (ResponseEntity<?>) authService.register(registerReqDto);
    }


}