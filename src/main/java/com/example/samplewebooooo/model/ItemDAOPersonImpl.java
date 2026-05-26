package com.example.samplewebooooo.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ItemDAOPersonImpl implements ItemDAO<Item> {

    
    @PersistenceContext  // Beanを取得し、自動でフィールドに割り当てる
    private EntityManager entityManager;

    public ItemDAOPersonImpl() {
        super();
    }

    /*
     *   全てのデータを取得する
     */
    @Override
    public List<Item> getAll() {

        // テーブルを指定
        Query query = entityManager.createQuery("from Item");
        
        // クエリの結果をリストに格納する
        List<Item> list = query.getResultList();

        // EntityManagerを閉じる
        entityManager.close();

        // リストを返す
        return list;
    }

    /*
     *   IDでデータを取得する
     */
    @Override
    public Item findById(long id) {

        // idに対応するデータを取得するクエリを実行する
        return (Item)entityManager.createQuery("from Item where id = " + id).getSingleResult();
    }

    /*
     *   名前でデータを取得する
     */
    @Override
    public List<Item> findByName(String name) {
        // nameに対応するデータを取得するクエリを実行する
        return (List<Item>)entityManager.createQuery("from Item where name = :name").setParameter("name", name).getResultList();
    }

    /*
    *   IDでデータを削除する
    */
    @Override
    public void deleteById(long id) {

         // idに対応するデータを取得し、削除する
        Item item = findById(id);

        // データが存在する場合は削除する
        if (item != null) {

            entityManager.remove(item);

        }

        // EntityManagerを閉じる
        entityManager.close();

    }

    /*
    *   データの合計を取得するクエリを実行する
    */
    @Override
    public long totalPrice() {

        // データベースから、価格と数量を掛け合わせた値の合計を取得するクエリを実行する
        return (long)entityManager.createQuery("SELECT SUM(price * volume) FROM Item").getSingleResult();
    }
    

    
}
