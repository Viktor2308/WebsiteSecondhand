package com.example.websitesecondhand.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
@Getter
@Setter
public class Image {
    @Id
    private String id;
    private Binary image;
}
