package com.example.websitesecondhand.controller;


import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UsersController usersController;

    private final NewPasswordDto newPasswordDto = new NewPasswordDto();

    @BeforeEach
    void init() {
        newPasswordDto.setCurrentPassword("oldPassword");
        newPasswordDto.setNewPassword("newPassword");
    }

    @DisplayName("Update correct password")
    @Test
    void shouldUpdateUserPassword() {
        when(userService.updateUserPassword(newPasswordDto)).thenReturn(true);
        ResponseEntity<Void> responseEntity = usersController.changeUserPassword(newPasswordDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @DisplayName("Update uncorrected user password")
    @Test
    void shouldNotUpdateUserPassword() {
        when(userService.updateUserPassword(newPasswordDto)).thenReturn(false);
        ResponseEntity<Void> responseEntity = usersController.changeUserPassword(newPasswordDto);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}