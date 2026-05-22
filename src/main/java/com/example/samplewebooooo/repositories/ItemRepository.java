package com.example.samplewebooooo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samplewebooooo.model.Item;

/* データベースにアクセスするためのクラス */
public interface ItemRepository extends JpaRepository<Item, Long> {

}
