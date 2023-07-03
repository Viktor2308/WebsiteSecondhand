package com.example.websitesecondhand.controller;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @Operation(summary = "Update password")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PostMapping("/users/set_password")
    ResponseEntity<Void> changeUserPassword(@RequestBody @Valid NewPasswordDto newPasswordDto) {
        log.info("Request update password");
        return userService.updateUserPassword(newPasswordDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @Operation(summary = "Get info about authorize user")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @GetMapping("/users/me")
    public ResponseEntity<UserDto> getUser() {
        log.info("Request GET info about authorize user");
        return ResponseEntity.ok().build(); //todo
    }

    @Operation(summary = "Обновить информацию об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {

        return ResponseEntity.ok().build(); //todo
    }
}
