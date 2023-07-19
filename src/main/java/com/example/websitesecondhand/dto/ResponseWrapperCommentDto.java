package com.example.websitesecondhand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperCommentDto {
    private int count;
    private Collection<CommentDto> results;
}
