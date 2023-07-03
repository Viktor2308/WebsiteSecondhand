package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.LoginReqDto;
import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.mapper.UserMapper;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
import com.example.websitesecondhand.service.AuthService;
import com.example.websitesecondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public boolean login(LoginReqDto loginReqDto) {
        UserDetails userDetails = userService.loadUserByUsername(loginReqDto.getUsername());
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(loginReqDto.getPassword(), encryptedPassword);
    }

    @Override
    public boolean register(RegisterReqDto registerReqDTO) {
        if (userRepository.findUserByUsername(registerReqDTO.getUsername()).isPresent()) {
            log.info("User {} is present", registerReqDTO.getUsername());
            return false;
        }
        User regUser = UserMapper.INSTANCE.registerReqDtoToUser(registerReqDTO);
        regUser.setPassword(encoder.encode(regUser.getPassword()));
        userRepository.save(regUser);
        log.info("User {} is create", registerReqDTO.getUsername());
        return true;
    }

}