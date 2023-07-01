package com.example.websitesecondhand.service;

import com.example.websitesecondhand.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<User> findAuthUser();
    UserDetails loadUserByUsername(String username);
}
