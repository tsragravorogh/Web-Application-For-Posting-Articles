package com.dataart.coreservice.service;

import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    Logger log = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ZipFileService zipFileService;

    public Article addArticle(MultipartFile multipartFile) {
        Article article = zipFileService.getArticleFromMultipartFile(multipartFile);
        log.trace("The article from .zip was created {}", article);
        return articleRepository.save(article);
    }

    public List<Article> getAllArticles() {
        log.trace("All articles returned");
        return articleRepository.findAllByOrderByCreatedDtDesc();
    }

    public List<Article> getAllArticleByTopic(String topic) {
        log.trace("All articles by topic returned. Topic: {}", topic);
        return articleRepository.findAllByTopic(topic);
    }
}