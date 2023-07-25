package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", expression = "java(getImage(comment))")
    CommentDto mapToCommentDto(Comment comment);

    default Long createdAt(LocalDateTime value) {
        return value != null
                ? value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                :  0L;
    }

    default String getImage(Comment comment) {
        return comment.getAuthor().getImage() != null
                ? "/users/image/" + comment.getAuthor().getId() + "/image"
                : null;
    }


}
