package com.example.websitesecondhand.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);
}
