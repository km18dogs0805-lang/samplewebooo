package com.example.samplewebooooo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class RakutenService {

    @Value("${rakuten.api.application-id}")
    private String applicationId;
    
    String[] params = {
        "format=json",
        "largeClassCode=japan",
        "middleClassCode=okinawa",
        "smallClassCode=nahashi",
        "applicationId=" + applicationId,
        "keyword=Java"
    };

    String url = "${rakuten.api.base-url}?" + String.join("&", params);
    
    // WebClientのインスタンスを作成
    private final WebClient webClient = WebClient.create();

    public Mono<String> searchBooks(String keyword) {

        System.out.println("${rakuten.api.base-url}"+"${rakuten.api.application-id}");
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
