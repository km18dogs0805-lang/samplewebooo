package com.example.samplewebooooo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.samplewebooooo.model.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ItemDAOPersonImpl implements ItemDAO<Item> {

    // Beanを取得し、自動でフィールドに割り当てる
    @PersistenceContext
    private EntityManager entityManager;

    public ItemDAOPersonImpl() {
        super();
    }

    @Override
    public List<Item> getAll() {
        // テーブルを指定
        Query query = entityManager.createQuery("from Item");
        
        List<Item> list = query.getResultList();

        entityManager.close();

        return list;
    }

    @Override
    public Item findById(long id) {
        // idに対応するデータを取得するクエリを実行する
        return (Item)entityManager.createQuery("from Item where id = " + id).getSingleResult();
    }

    @Override
    public List<Item> findByName(String name) {
        // nameに対応するデータを取得するクエリを実行する
        return (List<Item>)entityManager.createQuery("from Item where name = " + name).getResultList();
    }

    // データを削除
    @Override
    public void deleteById(long id) {
         // idに対応するデータを取得し、削除する
        Item item = findById(id);
        if (item != null) {
            entityManager.remove(item);
        }
    }

    @Override
    public long totalPrice() {
        // データベースから、価格と数量を掛け合わせた値の合計を取得するクエリを実行する
        return (long)entityManager.createQuery("SELECT SUM(price * volume) FROM Item").getSingleResult();
    }
    

    
}
