package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.NewPasswordDto;
import com.example.websitesecondhand.exception.InvalidOldPasswordException;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        log.info("Password " + user.getUsername() +" updated");
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


}