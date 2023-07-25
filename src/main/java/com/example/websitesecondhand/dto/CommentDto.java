package com.example.websitesecondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long pk;
    private long author;
    private String authorImage;
    private String authorFirstName;
    private int createdAt;
    private String text;
}
