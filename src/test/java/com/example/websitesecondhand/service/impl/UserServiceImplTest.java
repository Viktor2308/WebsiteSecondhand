package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.Role;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    UserServiceImpl userService;

    private final User user = new User();
    private final Image image = new Image();
    private final RegisterReqDto registerReqDto = new RegisterReqDto();
    private final NewPasswordDto newPasswordDto = new NewPasswordDto();

    @BeforeEach
    public void init() {
        byte[] mockImage = {1, 2, 3};
        image.setId("222");
        image.setImage(mockImage);

        user.setId(123);
        user.setUsername("UserName");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setFirstName("UserFirstName");
        user.setLastName("UserLastName");
        user.setPhone("89217654321");
        user.setImage(image);

        registerReqDto.setUsername("ReqDtoUsername");
        registerReqDto.setPassword("DtoPassword");
        registerReqDto.setFirstName("ReqDtoFirstName");
        registerReqDto.setLastName("ReqDtoLastName");
        registerReqDto.setPhone("89119119191");
        registerReqDto.setRole(Role.USER);

        newPasswordDto.setNewPassword("newPassword");
        newPasswordDto.setCurrentPassword("currentPassword");
    }

    @DisplayName("Test should return right UserDetails")
    @Test
    void shouldReturnUserByUsername() {
        String username = "UserName";

        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));

        UserDetails userDetailsTest = userService.loadUserByUsername(username);

        assertEquals(user.getUsername(), userDetailsTest.getUsername());
        assertEquals(user.getPassword(), userDetailsTest.getPassword());

    }


    @Test
    void returnOkWhenUpdatePassword() {
    }

    @Test
    void findAuthUser() {
    }

    @Test
    void checkIfValidOldPassword() {
    }

    @Test
    void changeUserPassword() {
    }
}