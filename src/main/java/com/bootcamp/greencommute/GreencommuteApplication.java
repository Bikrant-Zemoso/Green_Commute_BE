package com.bootcamp.greencommute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GreencommuteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreencommuteApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer( @Value("${greencommute.cors.origin}") final String serviceUrl){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins(serviceUrl);
			}
		};
	}
}
