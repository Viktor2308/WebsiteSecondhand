package com.example.websitesecondhand.service.impl;

import com.example.websitesecondhand.dto.AdsDto;
import com.example.websitesecondhand.dto.CreateAdsDto;
import com.example.websitesecondhand.dto.FullAdsDto;
import com.example.websitesecondhand.dto.ResponseWrapperAdsDto;
import com.example.websitesecondhand.mapper.AdsMapper;
import com.example.websitesecondhand.repository.AdsRepository;
import com.example.websitesecondhand.service.AdsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;

    @Override
    public ResponseWrapperAdsDto getAllAdsDto() {
        List<AdsDto> ads = adsRepository.findAllByOrderByIdDesc()
                .stream()
                .map(adsMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseWrapperAdsDto(ads.size(), ads);
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
