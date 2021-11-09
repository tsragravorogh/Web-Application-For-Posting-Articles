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
			articleRepository.save(new Article("Return to play: Tips to prevent COVID-19 transmission in sport and recreation facilities",
					"Since COVID-19 lockdowns began last year, the sport and recreation industry has been one of the hardest hit. And as we move towards a slow reopening, variants of concern (like the Delta variant) threaten the industry’s safe resumption. Despite vaccination efforts, some say herd immunity is unlikely, and we may have to learn to live with the virus.\n" +
							"\n" +
							"In Canada, lockdowns have had negative impacts on levels of physical activity, sedentary behaviour and mental health. And unhealthy lifestyle trends are associated with severe complications from COVID-19 and hospital admissions.\n" +
							"\n" +
							"Sport and recreation facilities help support healthy lifestyles. Throughout the pandemic, however, they’ve become difficult to safely operate because of the elevated respiratory activity and the potential for airborne transmission of the virus. As a result, many have been forced to close or operate under strict guidelines.\n" +
							"\n",
					"Sport"));
			articleRepository.save(new Article("New Zealand legalises assisted dying for the terminally ill",
					"New legislation supporting assisted dying has come into force in New Zealand, more than a year after almost two-thirds of New Zealanders voted in favour of the law in a referendum.\n" +
							"\n" +
							"The End of Life Choice Act will allow terminally ill patients to request doctors to end their lives.\n" +
							"\n" +
							"To be eligible, a person must have a terminal illness that is likely to end their life within six months or is in an \"advanced state of irreversible decline\". They must also be able to make an informed decision.\n" +
							"\n" +
							"Supporters believe the law will give New Zealanders who are \"suffering terribly at the end of their lives\" choice, compassion, and dignity.\n" +
							"\n" +
							"New Zealand's government has appointed three experts, including a medical ethicist, to monitor the legislation.",
					"Politics"));
			articleRepository.save(new Article("The Best (and Worst) Places in the World to\n" +
					"Get a Good Night's Sleep",
					"Things are very different in Cali, Columbia, where only 33% of stress tweets talk about not getting enough sleep. That makes Cali the most restful city on the planet. The news will come as a surprise to anyone familiar with the Columbian metropolis. Also known as Salsa Town, Cali has a well-earned reputation as a party city!\n" +
							"\n" +
							"Despite being one of Southeast Asia's financial hubs, Manilla is the second-best city for getting a proper night's sleep. Less than 36% of tweeters living in the capital of the Philippines have trouble nodding off after a long day crunching numbers and securing business deals.\n" +
							"\n" +
							"Other surprising entrants into the top 10 best cities for sleep include Cairo and Johannesburg. The metropolitan area of Cairo has a population of over 20million people, while nearly 6million call Johannesburg home. Yet these megacities quieten down after dark, which is why more than 60% of stress tweets in both places never mention sleep issues",
					"Travel"));
			articleRepository.save(new Article("Should I Invest in Individual Stocks?",
					"So you want to invest? AWESOME!!  Maybe you have been reading a cool blog like mine or perhaps you had a friend tell you about this wonderful wealth-building tool! Regardless, it will not be long before you question if you should buy individual stocks. With the bull market we are currently riding, there are many people that would tell you that it is a good idea. But is it really?\n" +
							"\n" +
							"Often times when new investors come to my office, they want to invest small amounts of money into individual stocks. They are really excited about the market and understandably so. I had one guy come in and tell me that he invested $40,000 in an individual stock that actually turned into $110,000. That’s awesome!! Granted it took him 5 years to do it, but nonetheless, he did it.\n" +
							"\n" +
							"Here’s the problem. When I asked him what type of account he was using, he looked at me funny and said, “I don’t know, I’m using cash.” Now, I’m not going to get into all the ramifications of this particular strategy, but there are two things to learn from his actions.",
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
