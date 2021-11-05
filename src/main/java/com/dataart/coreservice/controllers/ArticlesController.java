package com.dataart.coreservice.controllers;

import com.dataart.coreservice.dto.ArticleDto;
import com.dataart.coreservice.repository.ArticleRepository;
import com.dataart.coreservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    @Autowired
    private ArticleRepository articlesRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public ResponseEntity<?> getAllArticles() { return ResponseEntity.ok(articlesRepository.findAll()); }

    @PostMapping("/addArticle")
    public ResponseEntity<?> addArticle(@RequestBody ArticleDto articleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.addArticle(articleDto));
    }
}
