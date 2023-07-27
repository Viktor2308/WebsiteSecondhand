package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.Role;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Authentication authentication;

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
        image.setImage(new Binary(BsonBinarySubType.BINARY, mockImage));

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


    @Test
    void returnOkWhenUpdatePassword() {
    }

    @Test
    void findAuthUser() {
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        when(authentication.getName()).thenReturn("UserName");
        when(userRepository.findUserByUsername("UserName")).thenReturn(Optional.of(user));


        User result = userService.findAuthUser();

        verify(userRepository, times(1)).findUserByUsername("UserName");

        assertNotNull(result);
        assertEquals("UserName", result.getUsername());

    }

    @Test
    void checkIfValidOldPassword() {
    }

    @Test
    void changeUserPassword() {
    }

    @Test
    void getUserDto() {
    }

}