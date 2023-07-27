package com.example.websitesecondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {
    private String currentPassword;
    @NotBlank
    private String newPassword;
}
