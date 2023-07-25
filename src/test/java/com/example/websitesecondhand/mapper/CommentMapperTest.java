package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.model.Ads;
import com.example.websitesecondhand.model.Comment;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.model.User;
import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentMapperTest {
    private final Comment comment = new Comment();
    private final CommentDto commentDto = new CommentDto();
    private final User user = new User();
    private final Ads ads = new Ads();
    private final Image image = new Image();

    @BeforeEach
    public void setUp() {
        user.setId(1);
        user.setFirstName("UserName");
        user.setImage(image);

        ads.setId(123);

        image.setId("333");
        image.setImage(new Binary(new byte[0]));


        comment.setId(111);
        comment.setAuthor(user);
        comment.setAds(ads);
        comment.setText("test comment");

    }
    @DisplayName("Test: Mapping Comment to CommentDto")
    @Test
    void mapToCommentDto() {
        CommentDto commentDtoTest = CommentMapper.INSTANCE.mapToCommentDto(comment);

        assertEquals(comment.getId(), commentDtoTest.getPk());
        assertEquals(comment.getAuthor().getId(), commentDtoTest.getAuthor());
        assertEquals("/users/image/" + comment.getAuthor().getId() + "/image", commentDtoTest.getAuthorImage());
        assertEquals(comment.getAuthor().getFirstName(), commentDtoTest.getAuthorFirstName());
        assertEquals(comment.getText(), commentDtoTest.getText());
        assertEquals(0L, commentDtoTest.getCreatedAt());
    }

    @Test
    void createdAt() {
    }

    @Test
    void getImage() {
    }
}