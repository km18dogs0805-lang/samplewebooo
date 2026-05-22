package com.example.samplewebooooo;

import java.io.Serializable;
import java.util.List;

import com.example.samplewebooooo.model.Item;

public interface ItemDAO <T> extends Serializable {

    // 全データを取得
    public List<T> getAll();
    
    // idを取得
    public T findById(long id);

    // 名前を取得
    public List<T> findByName(String name);

    // データを削除
    public void deleteById(List<Item> itemList);

}
