package com.example.websitesecondhand.repository;

import com.example.websitesecondhand.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,String> {
}
