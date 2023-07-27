package com.example.websitesecondhand.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    private long id;
    private String text;
    private User author;
    private Ads ads;
    private LocalDateTime createdAt;
}
