package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.exception.ImageException;
import com.example.websitesecondhand.model.Image;
import com.example.websitesecondhand.repository.ImageRepository;
import com.example.websitesecondhand.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    @Override
    public Image saveImage(MultipartFile image) {
        Image newImage = new Image();
        try{
            byte[] bytes = image.getBytes();
            newImage.setImage(new Binary(BsonBinarySubType.BINARY, bytes));
        } catch (IOException e){
            e.printStackTrace();
        }
        newImage.setId(UUID.randomUUID().toString());
        return repository.save(newImage);
    }

    @Override
    public Image updateImage(MultipartFile newImage, String oldImageID) {
        Image oldImage = repository.findById(oldImageID).orElseThrow(() -> new ImageException("Image exception"));
        try {
            byte[] bytes = newImage.getBytes();
            oldImage.setImage(new Binary(BsonBinarySubType.BINARY, bytes));
        }catch (IOException e){
            e.printStackTrace();
        }
        return repository.save(oldImage);
    }

    @Override
    public byte[] getImage(String id) {
        Image image = repository.findById(id).orElseThrow(() -> new ImageException("Image exception"));
        return image.getImage().getData();
    }
}

