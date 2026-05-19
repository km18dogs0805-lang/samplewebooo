package com.example.samplewebooooo;

import java.io.Serializable;
import java.util.List;

public interface ItemDAO <T> extends Serializable {

    // 全データを取得
    public List<T> getAll();
    
    // idを取得
    public T findById(long id);

    // 名前を取得
    public List<T> findByName(String name);

}
