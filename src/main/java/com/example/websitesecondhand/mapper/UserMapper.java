package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "email")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapper")
    UserDto userToUserDto(User user);

    @Named("imageMapper")
    default String imageToString(Image image) {
        return image != null
                ? "/users/image/" + image.getId()
                : null;
    }



}
