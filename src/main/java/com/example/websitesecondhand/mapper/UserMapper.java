package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "email")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapper")
    UserDto userToUserDto(User user);
    @Named("imageMapper")
    default String imageToString(String image) {
        return image != null
                ? "/users/" + image + "/image"
                : null;
    }

    @Mapping(source = "email", target = "username")
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "role", ignore = true)
    User userDtoToUser(UserDto userDto);

}
