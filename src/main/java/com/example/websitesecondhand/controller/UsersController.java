package com.example.websitesecondhand.controller;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.service.ImageService;
import com.example.websitesecondhand.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
@RequestMapping("/")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final ImageService imageService;

    @Operation(summary = "Update password")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
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
    @GetMapping("/users/me")
    public ResponseEntity<UserDto> getUser(Authentication auth) {
        log.info("Request GET info about authorize user");
        return ResponseEntity.ok().body(userService.getUserDto(auth));
    }

    @Operation(summary = "Update info authorize user")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto user = userService.updateUserDto(userDto);
        return user != null
                ? ResponseEntity.ok(user)
                : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Operation(summary = "Update avatar authorize user")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserAvatar(@RequestBody @NotBlank MultipartFile image) throws IOException {
        log.info("Update avatar by user");
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get avatar")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
    @GetMapping(value = "/users/{id}/image/", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }
}
