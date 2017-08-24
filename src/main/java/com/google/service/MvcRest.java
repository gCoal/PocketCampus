package com.google.service;

import com.google.entity.Student;
import com.google.logic.NewsLogic;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController  //使用RestController注解时，不会返回视图，相当于每个方法默认都加了@ResponseBody
public class MvcRest {
    @Resource
    Student student;

    @Resource
    private NewsLogic newsLogic;


    @RequestMapping("/visit02/{id}")
    public String hello(@PathVariable(value = "id") int id) {
        System.out.println("参数为" + id);
        return "success";
    }


    @RequestMapping(value="/DBtest02", method= RequestMethod.POST)
    public JSONObject DBtest(){
        return  newsLogic.DbTest();
    }

    @RequestMapping(value="/PersistenceTest", method= RequestMethod.POST)
    public boolean PersistenceTest(){
        return  newsLogic.PersistenceTest();
    }

}
