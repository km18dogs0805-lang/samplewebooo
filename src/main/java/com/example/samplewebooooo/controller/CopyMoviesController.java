package com.example.samplewebooooo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.samplewebooooo.service.downloderMove;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class CopyMoviesController {

    @Autowired
    private downloderMove dlm;

    // moveList.htmlをマッピング
    @GetMapping("/moveList")
    public ModelAndView getMethodName(ModelAndView mav) {
        mav.setViewName("moveList");
        return mav;
    }
    

    @PostMapping("/moveList")
    public List<String> postMethodName(@RequestParam String url) throws Exception {
        
        //downloder.javaを実行
        return dlm.downloadVideo(url, "/tmp/downloads");
    }
    

    // 入力フォームからURLを送信 → ダウンロードされた動画の表示

}
