package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.model.Comment;
import com.example.websitesecondhand.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;
@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", source = "author.image", qualifiedByName = "imageMapper")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "text", source = "text")
    CommentDto commentToDto(Comment comment);

    default Long createdAt(LocalDateTime value) {
        return value != null
                ? value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                :  0L;
    }
    @Named("imageMapper")
    default String imageMapper(Image image) {
        return image != null
                ? "/users/image/" + image.getId()
                : null;
    }


}
