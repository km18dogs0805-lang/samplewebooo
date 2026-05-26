package com.example.samplewebooooo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.samplewebooooo.model.Item;
import com.example.samplewebooooo.model.ItemDAOPersonImpl;
import com.example.samplewebooooo.repositories.ItemRepository;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
*  SampleController: コントローラークラス
*/
@RestController
public class SampleController {

    // データが格納されたリポジトリ
    @Autowired
    ItemRepository repository;

    // データが格納されたDAO
    @Autowired
    ItemDAOPersonImpl dao;

    /* 
    *   mainDisplay: メイン画面を表示する
    */
    @RequestMapping("/")
    public ModelAndView mainDisplay(ModelAndView mav) {
        
        // main.htmlを表示
        mav.setViewName("main");
        
        // タイトルを表示
        mav.addObject("title", "Hello!!JPA");
        
        // 全データを取得
        List<Item> list = repository.findAll();
        
        // 全データを表示
        mav.addObject("data", list);
        
        return mav;
    }

    /* 
    *   resultDisplay: 更新結果を表示する
    */
    @PostMapping("/result")
    @Transactional  // トランザクション管理を有効にする
    public ModelAndView resultDisplay(@ModelAttribute("formModel") Item Item,
                                      ModelAndView mav
                                      ) 
    {

        // エンティティの更新  
        repository.saveAndFlush(Item);

        // 全データを表示
        List<Item> list = repository.findAll();

        // タイトルを表示
        mav.addObject("title", "更新しました");

        // dataに、全データを表示
        mav.addObject("data", list);

        // 更新後のresult.htmlを表示
        mav.setViewName("result");

        return mav;
    }

    /* 
    *   postMethodName: 更新結果を表示する
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
    
    /* 
    *   getMethodName: 検索結果を表示する
    */
    @GetMapping("/findresult")
    @Transactional  // トランザクション管理を有効にする
    public ModelAndView getFindResult(ModelAndView mav) {
        // タイトル表示
        mav.addObject("title", "検索結果");
        
        return mav;

    }

    /*
    *   getMethodName: 削除結果を表示する
    */ 
    @PostMapping("/delete_result/{id}")
    public ModelAndView getMethodName(@PathVariable long id, ModelAndView mav) {
        
        // 削除後のdelete_result.htmlを表示
        mav.setViewName("delete_result");

        // タイトルを表示
        mav.addObject("title", "削除しますか？");
        
        // データが存在するか確認してから削除
        if (repository.existsById(id)) {

            // データが存在する場合は削除
            repository.deleteById(id);

            // 削除後の全データを表示
            List<Item> list = repository.findAll();

            // 削除完了のメッセージを表示
            mav.addObject("message", "削除しました");

            // dataに、全データを表示
            mav.addObject("data", list);

        } else {
            
            // データが存在しない場合はエラーメッセージを表示
            mav.addObject("message", "データが見つかりませんでした");
            
        }

        return mav;
    }
    
    /*
     * 合計金額を表示する
     */ 
    @PostMapping("/sum_result")
    public ModelAndView sumMethodName(ModelAndView mav) {

        // 合計金額の結果を表示するsum_result.htmlを表示
        mav.setViewName("sum_result");

        // タイトルを表示
        mav.addObject("title", "合計金額");

        // 合計金額を取得
        long totalPrice = dao.totalPrice();

        // 合計金額を表示
        mav.addObject("totalPrice", totalPrice);

        return mav;

    }

}
