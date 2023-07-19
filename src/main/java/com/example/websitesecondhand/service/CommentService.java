package com.example.websitesecondhand.service;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.dto.ResponseWrapperCommentDto;

public interface CommentService {
    ResponseWrapperCommentDto getComments(int adsId);

    CommentDto addComment(int adsId, CommentDto commentDto);

    boolean deleteComment(int adsId, int commentId);

    CommentDto updateComment(int adsId, int commentId, CommentDto commentDto);
}
