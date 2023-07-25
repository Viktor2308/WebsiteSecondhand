package com.example.websitesecondhand.repository;


import com.example.websitesecondhand.model.Ads;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdsRepository extends MongoRepository<Ads, Long> {
}
