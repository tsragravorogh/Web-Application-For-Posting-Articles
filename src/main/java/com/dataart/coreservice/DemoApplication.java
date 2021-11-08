package com.dataart.coreservice;

import com.dataart.coreservice.entity.Article;
import com.dataart.coreservice.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ArticleRepository articleRepository) {
		return args -> {
			articleRepository.save(new Article("Sport - described as the most important of the unimportant things.",
					"While it may seem a long way off or irrelevant right now, later this year we could be gearing up for a thrilling 12 months of action.\n" +
							"\n" +
							"After rescheduling prompted by the coronavirus pandemic, the postponed Euros,...",
					"Sport"));
			articleRepository.save(new Article("The Fight for Feminism in Modern Day China",
					"Many political occurrences in modern day China have roots in the Maoist era. Physically, Mao’s presence looms over Beijing’s most prominent hallmark of political advocacy; ...",
					"Politics"));
			articleRepository.save(new Article("Travel 2021 Predictions",
					"Let’s be honest: Who in 2019 could have predicted that a microscopic organism would come out of the blue and like a wrecking ball turn our world upside down? Yet, here we are, hoping that the next year will be the ray of sunshine at the end...\n",
					"Travel"));
			articleRepository.save(new Article("The Innovators 2021",
					"This year’s innovators across cash management; trade, Islamic and corporate finance; and payments, demonstrated a renewed focus on speed to market, ease of use and the ability of customers to do more remotely using chatbots and mobile devices...",
					"Finance"));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
