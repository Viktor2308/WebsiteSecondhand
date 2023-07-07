package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.exception.ImageException;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.repository.ImageRepository;
import com.example.websitesecondhand.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    @Cacheable(value = "images", key = "#id")
    public Image getImage(String id) {
        return imageRepository.findById(id).orElseThrow(() ->
                new ImageException("Image not found"));
    }

    @Override
    @CachePut(value = "images", key = "#root.target.image.id")
    public Image addImage(MultipartFile file) {
        Image image = new Image();
        try {
            image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            image = imageRepository.save(image);
        } catch (IOException e) {
           throw new ImageException("Image no found");
        }
        return image;
    }
}
