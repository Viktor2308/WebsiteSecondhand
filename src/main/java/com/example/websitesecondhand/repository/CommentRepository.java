package com.example.websitesecondhand.repository;

import com.example.websitesecondhand.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAdsId(Long id);
}
