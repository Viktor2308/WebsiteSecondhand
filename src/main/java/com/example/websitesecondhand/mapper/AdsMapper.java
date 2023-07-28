package com.example.websitesecondhand.mapper;

import com.example.websitesecondhand.dto.AdsDto;
import com.example.websitesecondhand.dto.FullAdsDto;
import com.example.websitesecondhand.model.Ads;
import com.example.websitesecondhand.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageMapper")
    AdsDto toDto(Ads ads);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "author.image", qualifiedByName = "imageMapper")
    @Mapping(target = "email", source = "author.username")
    @Mapping(target = "pk", source = "id")
    FullAdsDto toFullAds(Ads ads);

    @Named("imageMapper")
    default String imageMapper(Image image) {
         return image != null
                ? "/users/image/" + image.getId()
                : null;
    }

}
