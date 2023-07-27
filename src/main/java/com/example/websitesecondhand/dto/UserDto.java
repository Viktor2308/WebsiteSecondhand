package com.example.websitesecondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
}
