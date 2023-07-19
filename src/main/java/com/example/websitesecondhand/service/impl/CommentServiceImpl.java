package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.dto.ResponseWrapperCommentDto;
import com.example.websitesecondhand.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseWrapperCommentDto getComments(int adsId) {
        return null;
    }

    @Override
    public CommentDto addComment(int adsId, CommentDto commentDto) {
        return null;
    }

    @Override
    public boolean deleteComment(int adsId, int commentId) {
        return false;
    }

    @Override
    public CommentDto updateComment(int adsId, int commentId, CommentDto commentDto) {
        return null;
    }
}
