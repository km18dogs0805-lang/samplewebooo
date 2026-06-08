package com.example.samplewebooooo.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@Service
public class RakutenService {

    // ✅ @Value の値はコンストラクタ実行後に注入されるため、
    //    フィールド宣言時には使わない
    @Value("${rakuten.api.base-url}")
    private URI baseUrl;

    @Value("${rakuten.api.application-id}")
    private String applicationId;

    @Value("${rakuten.api.access-key}")
    private String accessKey;
    
    public Mono<String> searchItems() {
        // ✅ baseUrl を直接使用
        String url = UriComponentsBuilder.fromUri(baseUrl)
                .queryParam("format", "json")
                .queryParam("largeClassCode", "japan")
                .queryParam("middleClassCode", "okinawa")
                .queryParam("smallClassCode", "nahashi")
                .queryParam("applicationId", applicationId)
                .queryParam("accessKey", accessKey)
                .toUriString();
                System.out.println("楽天API URL: " + url);

        return WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}