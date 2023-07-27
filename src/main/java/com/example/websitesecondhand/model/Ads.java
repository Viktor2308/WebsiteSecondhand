package com.example.websitesecondhand.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "ads")
@Getter
@Setter
@NoArgsConstructor
public class Ads {
    @Transient
    public static final String SEQUENCE_NAME = "ads_sequence";
    @Id
    private long id;
    private User author;
    private Image image;
    private int price;
    private String title;
    private String description;
    private Collection<Comment> comments = new ArrayList<>();
}
