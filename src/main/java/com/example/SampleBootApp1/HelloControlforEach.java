package com.example.SampleBootApp1;

import java.util.List;
//import org.hibernate.mapping.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SampleBootApp1.repositories.PersonRepository;

//import ch.qos.logback.core.model.Model;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class HelloControlforEach {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonDAOPersonImp dao;

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(
        HttpServletRequest request,
        ModelAndView mav
    ) {
        // View
        mav.setViewName("find");
        
        // getParameter
        String param = request.getParameter("find_str");

        // result
        if(param == "") {

            // redirect
            mav = new ModelAndView("redirect:/find");

        } else {
            // search from dao
            List<Person> list = dao.find(param);

            // Add Object
            mav.addObject("data", list);
        }

        return mav;
    }

    
    
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("find");
        mav.addObject("msg", "Personのさんぷるです。");
        Iterable<Person> list = dao.getAll();
        mav.addObject("data", list);
        return mav;
    }
    
    @RequestMapping("/")
    public ModelAndView index(
        @ModelAttribute("formModel") Person person, 
        ModelAndView mav
    ) {
        mav.setViewName("index");
        mav.addObject("title", "Hello Spring Boot");
        mav.addObject("msg", "これは、JPAのサンプルです。");
        // エンティティからすべてを取り出す
        List<Person> list = dao.getAll();
        //List<Person> list = repository.findAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    @Transactional
    public ModelAndView form(
        @ModelAttribute("formModel") @Validated Person person, 
        BindingResult result,
        ModelAndView mav
    ) {
        ModelAndView res = null;
        System.out.println(result.getFieldErrors());
        if(!result.hasErrors()) {
            repository.saveAndFlush(person);
            res = new ModelAndView("redirect:/");
        } else {
            mav.setViewName("index");
            mav.addObject("title", "Hello Page");
            mav.addObject("msg", "エラーが発生しました。");
            Iterable<Person> list = repository.findAll();
            mav.addObject("datalist", list);
            res = mav;
        }
        return res;
    }

    @PostConstruct
    public void init() {
        Person p1 = new Person();
        p1.setName("taro");
        p1.setAge(30);
        p1.setMail("taro@yamada");
        repository.saveAndFlush(p1);

        Person p2 = new Person();
        p2.setName("hanako");
        p2.setAge(25);
        p2.setMail("hanako@suzuki");
        repository.saveAndFlush(p2);

        Person p3 = new Person();
        p3.setName("sachiko");
        p3.setAge(35);
        p3.setMail("sachiko@tanaka");
        repository.saveAndFlush(p3);
    }

    // /edit用
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(
        @ModelAttribute Person person,
        @PathVariable int id,
        ModelAndView mav
    ) {
        mav.setViewName("edit");
        mav.addObject("title", "edit Person");
        Optional<Person> data = repository.findById((long)id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/edit", method=RequestMethod.POST)
    @Transactional
    public ModelAndView update(
        @ModelAttribute Person person,
        ModelAndView mav
    ) {
        repository.saveAndFlush(person);
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(
        @PathVariable int id,
        ModelAndView mav
    ) {
        mav.setViewName("delete");

        // title
        mav.addObject("title", "Delete Person");

        // msg
        mav.addObject("msg", "Can I delete this record？");

        // エンティティを検索
        Optional<Person> data = repository.findById((long)id);

        // インスタンスを取得
        mav.addObject("formModel", data.get());

        // return
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional  // トランザクションの開始
    public ModelAndView remove(
        @RequestParam long id,
        ModelAndView mav
    ) {
        // リポジトリから削除
        repository.deleteById(id);

        // リダイレクト
        return new ModelAndView("redirect:/");
    }
}
