package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.exception.InvalidOldPasswordException;
import com.example.websitesecondhand.mapper.UserMapper;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
import com.example.websitesecondhand.service.ImageService;
import com.example.websitesecondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + username + " doesn't exist"));
    }

    @Override
    public boolean updateUserPassword(NewPasswordDto newPasswordDto) {
        User user = findAuthUser();
        if (!checkIfValidOldPassword(user, newPasswordDto.getCurrentPassword())) {
            throw new InvalidOldPasswordException("Current password is not valid");
        }
        changeUserPassword(user, newPasswordDto.getNewPassword());
        log.info("Password " + user.getUsername() + " updated");
        return true;
    }

    @Override
    public User findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findUserByUsername(currentPrincipalName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public UserDto getUserDto() {
        User user = findAuthUser();
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto updateUserDto(UserDto userDto) {
        userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
        return userDto;
    }

    @Override
    public String updateUserImage(MultipartFile file){
        Image image = null;
        try {
            image = imageService.addImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = findAuthUser();
        user.setImage(image);
        userRepository.save(user);
        return image.getId();
    }


}