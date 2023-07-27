package com.example.websitesecondhand.service;

import com.example.websitesecondhand.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image saveImage(MultipartFile image);

    Image updateImage(MultipartFile image, String oldImage);

    byte[] getImage(String id);
}
