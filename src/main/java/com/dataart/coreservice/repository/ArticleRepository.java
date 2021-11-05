package com.dataart.coreservice.repository;

import com.dataart.coreservice.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    List<Article> findAll();

}
