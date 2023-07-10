package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.RegisterReqDto;
import com.example.websitesecondhand.dto.UserDto;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.Role;
import com.example.websitesecondhand.model.User;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserDto userDto = new UserDto();
    private final User user = new User();
    private final Image image= new Image();
    private final RegisterReqDto registerReqDto = new RegisterReqDto();

    @BeforeEach
    public void setUp() {
        userDto.setId(1);
        userDto.setEmail("test@test.ru");
        userDto.setFirstName("FirstNameUserDto");
        userDto.setLastName("LastNameUserDto");
        userDto.setPhone("89211234567");
        userDto.setImage("imageUserDto");

        byte[] mockImage = {1, 2, 3};
        image.setId("222");
        image.setImage(new Binary(BsonBinarySubType.BINARY, mockImage));

        user.setId(123);
        user.setUsername("UserName");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setFirstName("UserFirstName");
        user.setLastName("UserLastName");
        user.setPhone("89217654321");
        user.setImage(image);

        registerReqDto.setUsername("ReqDtoUsername");
        registerReqDto.setPassword("DtoPassword");
        registerReqDto.setFirstName("ReqDtoFirstName");
        registerReqDto.setLastName("ReqDtoLastName");
        registerReqDto.setPhone("89119119191");
        registerReqDto.setRole(Role.USER);
    }
    @DisplayName("Test: Mapping User to UserDto")
    @Test
    void userToUserDto() {
        UserDto userDtoTest = UserMapper.INSTANCE.userToUserDto(user);

        assertEquals(user.getId(), userDtoTest.getId());
        assertEquals(user.getUsername(),userDtoTest.getEmail() );
        assertEquals(user.getFirstName(), userDtoTest.getFirstName() );
        assertEquals(user.getLastName(), userDtoTest.getLastName() );
        assertEquals(user.getPhone(),userDtoTest.getPhone() );
        assertEquals("/users/" + user.getImage().getId() + "/image", userDtoTest.getImage() );
    }
    @DisplayName("Test: Mapping UserDto to User")
    @Test
    void userDtoToUser() {
        User userTest = UserMapper.INSTANCE.userDtoToUser(userDto);

        assertEquals(userDto.getId(), userTest.getId());
        assertEquals(userDto.getEmail(), userTest.getUsername());
        assertEquals(userDto.getFirstName(), userTest.getFirstName());
        assertEquals(userDto.getLastName(), userTest.getLastName());
        assertEquals(userDto.getPhone(), userTest.getPhone());

    }

}