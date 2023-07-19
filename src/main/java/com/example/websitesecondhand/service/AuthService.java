package com.example.websitesecondhand.service;


import com.example.websitesecondhand.dto.LoginReqDto;
import com.example.websitesecondhand.dto.RegisterReqDto;

public interface AuthService {
    Object authenticateUser(LoginReqDto loginReqDto);
    Object register(RegisterReqDto registerReqDTO);
}
