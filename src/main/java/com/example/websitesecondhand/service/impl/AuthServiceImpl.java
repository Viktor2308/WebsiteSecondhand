package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.MongoUtil.SequenceGeneratorService;
import com.example.websitesecondhand.dto.JwtResponse;
import com.example.websitesecondhand.dto.LoginReqDto;
import com.example.websitesecondhand.dto.MessageResponse;
import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.model.Role;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
import com.example.websitesecondhand.config.jwt.JwtUtils;
import com.example.websitesecondhand.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Object authenticateUser(LoginReqDto loginReqDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqDto.getUsername(), loginReqDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public Object register(RegisterReqDto registerReqDTO) {
        if (userRepository.existsByUsername(registerReqDTO.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        User user = new User();
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setUsername(registerReqDTO.getUsername());
        user.setPassword(encoder.encode(registerReqDTO.getPassword()));
        user.setFirstName(registerReqDTO.getFirstName());
        user.setLastName(registerReqDTO.getLastName());
        user.setPhone(registerReqDTO.getPhone());
        if(registerReqDTO.getRole()==null){
            user.setRole(Role.USER);
        } else {
            user.setRole(registerReqDTO.getRole());
        }
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}