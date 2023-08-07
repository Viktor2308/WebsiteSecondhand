package com.example.websitesecondhand.repository;


import com.example.websitesecondhand.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Long> {

    List<Ads> findAllByOrderByIdDesc();

}
