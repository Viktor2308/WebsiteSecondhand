package com.example.websitesecondhand.service;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {
    User findAuthUser();
    boolean updateUserPassword(NewPasswordDto newPasswordDto);

    boolean checkIfValidOldPassword(final User user, final String oldPassword);

    void changeUserPassword(final User user, final String password);

    UserDto getUserDto();

    UserDto updateUserDto(UserDto userDto);

    void updateUserImage(MultipartFile image) throws IOException;
}
