package com.example.samplewebooooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleweboooooApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleweboooooApplication.class, args);
		System.out.println("実行エリート官僚！！");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
