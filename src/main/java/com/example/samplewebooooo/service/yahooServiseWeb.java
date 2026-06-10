package com.example.samplewebooooo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class yahooServiseWeb {

    @Value("${yahoo.api.base-url}")
    private String baseUrl;

    @Value("${yahoo.api.client-id}")
    private String appId;

    private final WebClient webClient;

    public yahooServiseWeb(@Qualifier("yahooWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Yahoo ショッピング API で商品を検索し、結果リストを返す
     *
     * URL例: https://shopping.yahooapis.jp/ShoppingWebService/V3/itemSearch
     *         ?appid={client-id}&query={keyword}&results=20
     *
     * @param keyword 検索キーワード
     * @return 商品情報 (name, price, url) のリスト
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> searchItem(String keyword) {

        // application.properties の base-url と client-id を使って URL を組み立てる
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("appid", appId)
                .queryParam("query", keyword)
                .queryParam("results", 50)
                .build()
                .toUriString();

        System.out.println("Yahoo API リクエストURL: " + url);

        // API レスポンス全体を Map として受け取る
        Map<String, Object> response = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, String>> items = new ArrayList<>();
        if (response == null) return items;

        // V3 の hits は Map ではなく直接 List（配列）
        List<Map<String, Object>> hitList = (List<Map<String, Object>>) response.get("hits");
        if (hitList == null) return items;

        for (Map<String, Object> hit : hitList) {
            String name  = String.valueOf(hit.getOrDefault("name",  "（名称不明）"));
            String price = String.valueOf(hit.getOrDefault("price", "0"));
            String itemUrl = String.valueOf(hit.getOrDefault("url", "#"));

            items.add(Map.of("name", name, "price", price, "url", itemUrl));
        }

        return items;
    }
}
