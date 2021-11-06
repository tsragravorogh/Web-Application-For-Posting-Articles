package com.dataart.coreservice.mapper;

import com.dataart.coreservice.dto.ArticleDto;
import com.dataart.coreservice.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

    @Mapping(target = "title", source = "article.title")
    @Mapping(target = "body", source = "article.body")
    @Mapping(target = "createdDt", source = "article.createdDt")
    ArticleDto toDto(Article article);
}
