package com.example.samplewebooooo.model;

import java.beans.JavaBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {

    /*フィールドのId値*/ 
    @Id
    // 自動で値を割り振る
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    /* フィールド */

    // タイトル
    @Column(length = 50, nullable = false)
    private String name;

    // 価格
    @Column(length = 10, nullable = false)
    private Integer price;

    // 数量
    @Column(length = 3, nullable = false)
    private Integer volume;

    /* Getter及びSetter */
        // フィールドのId値
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // タイトル
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // 価格
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    // 数量
     public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

}
