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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;
    private final UserMapper userMapper;


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
        User principal = (User) authentication.getPrincipal();
        String username;
        if (principal != null) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findUserByUsername(username).orElseThrow(() ->
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
    public UserDto getUserDto(Authentication authentication) {
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        UserDto userDto = userMapper.userToUserDto(user);
        log.info("User DTO: {}", userDto.toString());
        return userDto;
    }

    @Override
    public UserDto updateUserDto(UserDto userDto) {
        User user = userRepository.findUserByUsername(userDto.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void updateUserImage(MultipartFile file) {
        log.info("New avatar {}", file.getName());
        User user = findAuthUser();
        Image newImage;
        if (userRepository.findUserByUsername(user.getUsername()).get().getImage() == null) {
            newImage = imageService.saveImage(file);
        } else {
            newImage = imageService.updateImage(file, user.getImage());
        }
        user.setImage(newImage);
        userRepository.save(user);
    }
}