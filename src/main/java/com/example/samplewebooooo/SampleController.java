package com.example.samplewebooooo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.samplewebooooo.model.Item;
import com.example.samplewebooooo.repositories.ItemRepository;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class SampleController {

    // データが格納されたリポジトリ
    @Autowired
    ItemRepository repository;

    @Autowired
    ItemDAOPersonImpl dao;

    @RequestMapping("/")
    public ModelAndView mainDisplay(ModelAndView mav
                                    ) {
        
        mav.setViewName("main");
        
        mav.addObject("title", "Hello!!JPA");
        
        // 全データを取得
        List<Item> list = repository.findAll();
        //List<Item> list = dao.getAll();
        System.out.println("id : " + repository.count());
        
        // 全データを表示
        mav.addObject("data", list);
    
        return mav;
    }

    @PostMapping("/result")
    @Transactional
    public ModelAndView resultDisplay(@ModelAttribute("formModel") Item Item,
                                      ModelAndView mav
                                      ) 
    {
        //TODO: process POST request

        // エンティティの更新  
        repository.saveAndFlush(Item);

        // 全データを表示
        List<Item> list = repository.findAll();
        
        //Item list = new Item();

        mav.addObject("title", "更新しました");

        mav.addObject("data", list);

        // 更新後のresult.htmlを表示
        mav.setViewName("result");
        return mav;
    }
    
    // 戻る

    /* 
    *   Item item: モデル
    */
    @PostMapping("/main")
    public ModelAndView postMethodName(@ModelAttribute("formModel") Item item, ModelAndView mav) {
        
        // タイトルを表示
        mav.addObject("title", "更新結果");
        
        // DBに存在する全データ
        List<Item> list = dao.getAll();

        // デバッグ
        System.out.println("最後の値は・・・" + dao.getAll());
        
        // レコードに表示
        mav.addObject("data", list);
        
        // 更新後のmain.htmlを表示
        mav.setViewName("main");
        return mav;
    }
    
    // find.html
    @GetMapping("/find")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    // リポジトリの全データを削除
    @PostMapping("/delete_result")
    public String postMethodName(@ModelAttribute("formModel") Item item) {
        //TODO: process POST request
        List<Item> list = dao.getAll();
        dao.deleteById(list);
        return "delete_result";
    }
    
    

}
