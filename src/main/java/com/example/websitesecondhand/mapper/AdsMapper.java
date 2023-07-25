package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.AdsDto;
import com.example.websitesecondhand.model.Ads;
import com.example.websitesecondhand.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", expression = "java(ads.getAuthor().getId())")
    AdsDto AdsToAdsDTO(Ads ads);

    default String imageToString(Image image) {
        return image != null
                ? "/ads/" + image.getId() + "/image"
                : null;
    }

}
