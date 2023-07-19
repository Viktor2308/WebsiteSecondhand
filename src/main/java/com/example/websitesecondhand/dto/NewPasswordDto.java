package com.example.websitesecondhand.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {
    private String currentPassword;
    @NotBlank
    private String newPassword;
}
