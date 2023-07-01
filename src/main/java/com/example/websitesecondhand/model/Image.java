package com.example.websitesecondhand.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
@Data
public class Image {
    @Id
    private String id;
    private byte[] image;
}
