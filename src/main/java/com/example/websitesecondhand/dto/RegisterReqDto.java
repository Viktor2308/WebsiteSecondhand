package com.example.websitesecondhand.dto;

import com.example.websitesecondhand.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDto {
    @NotBlank
    @Size(max = 30)
    private String username;
    @NotBlank
    @Size(max = 120)
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;
    private Role role;
}
