package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "email")
    UserDto userToUserDto(User user);

    default String imageToString(Image image) {
        return image != null
                ? "/users/" + image.getId() + "/image"
                : null;
    }

    @Mapping(source = "email", target = "username")
    @Mapping(target = "image", ignore = true)
    User userDtoToUser(UserDto userDto);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "image", ignore = true)
    User registerReqDtoToUser(RegisterReqDto registerReqDto);
}
