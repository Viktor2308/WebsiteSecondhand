package com.example.websitesecondhand.service;


import com.example.websitesecondhand.dto.LoginReqDto;
import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.dto.RegisterReqDto;

public interface AuthService {
    boolean login(LoginReqDto loginReqDto);
    boolean register(RegisterReqDto registerReqDTO);
    void changePassword(NewPasswordDto newPasswordDto);
}
