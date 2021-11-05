package com.dataart.coreservice.mapper;

import com.dataart.coreservice.dto.ArticleDto;
import com.dataart.coreservice.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

    @Mapping(source = "articleDto.title", target = "title")
    @Mapping(source = "articleDto.body", target = "body")
    @Mapping(source = "articleDto.topic", target = "topic")
    @Mapping(source = "articleDto.createdDt", target = "createdDt")
    Article toEntity(ArticleDto articleDto);
}
