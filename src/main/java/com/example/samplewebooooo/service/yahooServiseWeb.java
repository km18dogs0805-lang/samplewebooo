package com.example.samplewebooooo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *  検索結果を出力するクラス
 */
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
     * @return items 商品情報 (name, price, url) のリスト
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> searchItem(String keyword, long number) {

        // application.properties の base-url と client-id を使って URL を組み立てる
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("appid", appId)
                .queryParam("query", keyword)
                .queryParam("results", number)
                .build()
                .toUriString();

        System.out.println("Yahoo API リクエストURL: " + url);

        // API レスポンス全体を Map として受け取る
        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Mapに格納したデータ（レスポンスされた結果）
        List<Map<String, String>> items = new ArrayList<>();

        // キーが取得できない場合
        if (response == null) return items;

        // responseで得られたデータを、hitList変数に格納する
        List<Map<String, Object>> hitList = (List<Map<String, Object>>) response.get("hits");

        // 検索結果がゼロの場合
        if (hitList == null) return items;

        // JSONから得た値を、String型に変換し、キーとして設定する
        for (Map<String, Object> hit : hitList) {

            // indexが存在しない：：０を出力
            Object defIndex = hit.getOrDefault("index", "0");
            String index = String.valueOf(defIndex);

            // nameが存在しない：：（名称不明）を出力
            Object defName = hit.getOrDefault("name",  "（名称不明）");
            String name  = String.valueOf(defName);

            // priceが存在しない：：０を出力
            Object defPrice = hit.getOrDefault("price", "0");
            String price = String.valueOf(defPrice);
            
            // urlが存在しない：：＃を出力
            Object defItemUrl = hit.getOrDefault("url", "#");
            String itemUrl = String.valueOf(defItemUrl);
            
            // マッピングされたデータをまとめて List に追加
            items.add(Map.of("index",index,"name", name, "price", price, "url", itemUrl));
        }

        return items;
    }
}
