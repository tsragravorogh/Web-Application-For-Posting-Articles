package com.dataart.coreservice.controllers;

import com.dataart.coreservice.repository.ArticleRepository;
import com.dataart.coreservice.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class ArticlesController {

    Logger log = LoggerFactory.getLogger(ArticlesController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getAllArticles")
    public ResponseEntity<?> getAllArticles() {
        log.info("The method 'getAllArticles' was called");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.getAllArticles());
    }

    @PostMapping("/addArticle")
    public ResponseEntity<?> addArticle(@RequestParam("file") MultipartFile multipartFile) {
        log.info("The method 'addArticle' was called. File: {}", multipartFile.getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.addArticle(multipartFile));
    }

    @GetMapping("/getAllArticles/{topic}")
    public ResponseEntity<?> getAllArticleByTopic(@PathVariable String topic) {
        log.info("The method 'getAllArticleByTopic' was called. Topic: {}", topic);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.getAllArticleByTopic(topic));
    }
}
