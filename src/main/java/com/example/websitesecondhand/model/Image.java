package com.example.websitesecondhand.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {

    @Id
    private String id;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
}
