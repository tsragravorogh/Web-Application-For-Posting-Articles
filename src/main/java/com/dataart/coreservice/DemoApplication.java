package com.dataart.coreservice;

import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ArticleRepository articleRepository) {
		return args -> {
			articleRepository.save(new Article("Topic1", "body"));
			articleRepository.save(new Article("Topic2", "body2"));
			articleRepository.save(new Article("Topic2", "body3"));
			articleRepository.save(new Article("Topic2", "body4"));
		};
	}

}
