package com.dataart.coreservice.service;

import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.exception.IncorrectZipFileException;
import com.dataart.coreservice.mapper.ArticleMapper;
import com.dataart.coreservice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.zip.ZipFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ZipFileService zipFileService;

    public Article addArticle(MultipartFile multipartFile) {
        Article article = zipFileService.getArticleFromMultipartFile(multipartFile);
        return articleRepository.save(article);
    }

    public Article addArticle(ZipFile file) {
        Article article = zipFileService.getArticleFromZip(file);
        return articleRepository.save(article);
    }

}