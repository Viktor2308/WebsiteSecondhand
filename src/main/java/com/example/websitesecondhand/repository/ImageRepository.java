package com.example.websitesecondhand.repository;

import com.example.websitesecondhand.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image,String> {
}
