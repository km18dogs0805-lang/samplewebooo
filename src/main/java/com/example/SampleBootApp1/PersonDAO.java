package com.example.SampleBootApp1;

import java.io.Serializable;
import java.util.List;

public interface PersonDAO <T> extends Serializable {

    // すべてのエンティティを取得する
    public List<T> getAll();

    // idでエンティティを取得する
    public T findById(long id);

    // 名前からエンティティを検索
    public List<T> findByName(String name);

    public List<T> find(String fstring);
}
