package com.dataart.coreservice.controllers;

import com.dataart.coreservice.repository.ArticleRepository;
import com.dataart.coreservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.zip.ZipFile;

@RestController
@RequestMapping("/api/")
public class ArticlesController {

    @Autowired
    private ArticleRepository articlesRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticles() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articlesRepository.findAll());
    }

    @PostMapping("/addArticle")
    public ResponseEntity<?> addArticle(@RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.addArticle(multipartFile));
    }
}
