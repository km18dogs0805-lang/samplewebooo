package com.example.samplewebooooo;

import java.util.List;

import org.springframework.stereotype.Repository;

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
        // TODO Auto-generated method stub
        return (Item)entityManager.createQuery("from Item where id = " + id).getResultList();
    }

    @Override
    public List<Item> findByName(String name) {
        // TODO Auto-generated method stub
        return (List<Item>)entityManager.createQuery("from Item where name = " + name).getResultList();
    }

    // idを取得
    

    
}
