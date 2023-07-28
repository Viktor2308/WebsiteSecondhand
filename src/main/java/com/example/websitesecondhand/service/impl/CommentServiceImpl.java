package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.dto.ResponseWrapperCommentDto;
import com.example.websitesecondhand.exception.AdsNotFoundException;
import com.example.websitesecondhand.mapper.CommentMapper;
import com.example.websitesecondhand.model.Ads;
import com.example.websitesecondhand.model.Comment;
import com.example.websitesecondhand.repository.AdsRepository;
import com.example.websitesecondhand.repository.CommentRepository;
import com.example.websitesecondhand.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public ResponseWrapperCommentDto getComments(int adsId) {
             List<CommentDto> commentDtoList = commentRepository.findAllByAdsId((long)adsId)
                     .stream()
                     .map(commentMapper::commentToDto)
                     .collect(Collectors.toList());

        return new ResponseWrapperCommentDto(commentDtoList.size(), commentDtoList);
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
