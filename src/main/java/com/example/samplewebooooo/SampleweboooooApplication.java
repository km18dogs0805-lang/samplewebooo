package com.example.samplewebooooo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleweboooooApplication {
	/**
	 * SpringBootプロジェクトを実行する
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(SampleweboooooApplication.class, args);

	}
	/**
	 * Rest通信を行うためのクラス（WebClientなど、Http通信を行う）
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
		
	}

}
