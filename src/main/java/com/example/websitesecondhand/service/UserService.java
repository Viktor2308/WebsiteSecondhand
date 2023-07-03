package com.example.websitesecondhand.service;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.model.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    User findAuthUser();
    UserDetails loadUserByUsername(String username);
    boolean updateUserPassword(NewPasswordDto newPasswordDto);
    boolean checkIfValidOldPassword(final User user, final String oldPassword);
    void changeUserPassword(final User user, final String password);
}
