package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.MongoUtil.SequenceGeneratorService;
import com.example.websitesecondhand.dto.LoginReqDto;
import com.example.websitesecondhand.dto.MessageResponse;
import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.model.Role;
import com.example.websitesecondhand.model.User;
import com.example.websitesecondhand.repository.UserRepository;
import com.example.websitesecondhand.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public boolean authenticateUser(LoginReqDto loginReqDto) {
        log.info("New login:{}", loginReqDto.getUsername());
        UserDetails userDetails = userRepository.findUserByUsername(loginReqDto.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException("User with username " + loginReqDto.getUsername() + " doesn't exists"));
        return encoder.matches(loginReqDto.getPassword(), userDetails.getPassword());
    }

    @Override
    public Object register(RegisterReqDto registerReqDTO) {
        log.info("New request for register:{},{}}",registerReqDTO.getUsername(), registerReqDTO.getRole());
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