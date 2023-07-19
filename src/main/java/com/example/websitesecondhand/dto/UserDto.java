package com.example.websitesecondhand.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
}
