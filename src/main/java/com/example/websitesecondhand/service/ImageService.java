package com.example.websitesecondhand.service;

import com.example.websitesecondhand.model.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
    Image saveImage(MultipartFile image);

    Image updateImage(MultipartFile image, Image oldImage);

    byte[] getImage(String id);
}
