package com.example.websitesecondhand.service;

import com.example.websitesecondhand.dto.AdsDto;
import com.example.websitesecondhand.dto.CreateAdsDto;
import com.example.websitesecondhand.dto.FullAdsDto;
import com.example.websitesecondhand.dto.ResponseWrapperAdsDto;
import org.springframework.web.multipart.MultipartFile;

public interface AdsService {
    ResponseWrapperAdsDto getAllAdsDto();

    AdsDto addAds(CreateAdsDto properties, MultipartFile image);

    void removeAdsDto(int adsId);

    AdsDto updateAdsDto(int adsId, CreateAdsDto createAdsDto);

    ResponseWrapperAdsDto getAllAdsMe();

    byte[] updateImageAdsDto(int adsId, MultipartFile image);

    FullAdsDto getFullAds(int adsId);
}
