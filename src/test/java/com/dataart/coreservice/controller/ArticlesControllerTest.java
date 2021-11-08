package com.dataart.coreservice.controller;

import com.dataart.coreservice.controllers.ArticlesController;
import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.repository.ArticleRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ArticlesController.class)
public class ArticlesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleRepository articleRepository;

    @Test
    public void verifyThatGetMethodGetAllArticlesWorksCorrectly() throws Exception {
        Article testArticle = new Article("The best game in the world", "It was Legendary","Sport");
        List<Article> articlesList = Collections.singletonList(testArticle);
        given(articleRepository.findAllByOrderByCreatedDt()).willReturn(articlesList);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/getAllArticles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verifyThatGetMethodGetAllArticleByTopicWorksCorrectly() throws Exception {
        String topic = "Sport";
        String forArticle = RandomStringUtils.random(10);
        Article testArticle = new Article(forArticle, forArticle, topic);
        List<Article> articlesList = Collections.singletonList(testArticle);
        given(articleRepository.findAllByOrderByCreatedDt()).willReturn(articlesList);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/getAllArticles/{topic}", topic)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
