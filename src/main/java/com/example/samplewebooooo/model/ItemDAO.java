package com.example.samplewebooooo.model;

import java.io.Serializable;
import java.util.List;

public interface ItemDAO <T> extends Serializable {

    // 全データを取得
    public List<T> getAll();
    
    // idを取得
    public T findById(long id);

    // 名前を取得
    public List<T> findByName(String name);
    
    // 名前、IDの検索
    public List<Item> find(String fstr);

    // データを削除
    public void deleteById(long id);

    // データの合計
    public long totalPrice();

}
