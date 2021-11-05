package com.dataart.coreservice.service;

import com.dataart.coreservice.dto.ArticleDto;
import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.mapper.ArticleMapper;
import com.dataart.coreservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    public Article addArticle(ArticleDto articleDto) {
        return articleRepository.save(articleMapper.toEntity(articleDto));
    }
}