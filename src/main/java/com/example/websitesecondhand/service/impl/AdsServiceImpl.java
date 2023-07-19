package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.AdsDto;
import com.example.websitesecondhand.dto.CreateAdsDto;
import com.example.websitesecondhand.dto.FullAdsDto;
import com.example.websitesecondhand.dto.ResponseWrapperAdsDto;
import com.example.websitesecondhand.service.AdsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        return null;
    }

    @Override
    public AdsDto addAds(CreateAdsDto properties, MultipartFile image) {
        return null;
    }

    @Override
    public void removeAdsDto(int adsId) {

    }

    @Override
    public AdsDto updateAdsDto(int adsId, CreateAdsDto createAdsDto) {
        return null;
    }

    @Override
    public ResponseWrapperAdsDto getAllAdsMe() {
        return null;
    }

    @Override
    public byte[] updateImageAdsDto(int adsId, MultipartFile image) {
        return new byte[0];
    }

    @Override
    public FullAdsDto getFullAds(int adsId) {
        return null;
    }
}
