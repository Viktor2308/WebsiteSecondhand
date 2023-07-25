package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.dto.ResponseWrapperCommentDto;
import com.example.websitesecondhand.exception.AdsNotFoundException;
import com.example.websitesecondhand.mapper.CommentMapper;
import com.example.websitesecondhand.model.Ads;
import com.example.websitesecondhand.model.Comment;
import com.example.websitesecondhand.repository.AdsRepository;
import com.example.websitesecondhand.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final AdsRepository adsRepository;
    @Override
    public ResponseWrapperCommentDto getComments(int adsId) {
        Ads ads = adsRepository.findById((long) adsId).orElseThrow(AdsNotFoundException::new);
        Collection<Comment> commentList = ads.getComments();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDtoList.add(CommentMapper.INSTANCE.mapToCommentDto(comment));
        }
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
