package com.google.service;

import com.google.entity.Student;
import com.google.logic.NewsLogic;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpringMvcRest {
    @Resource
    private Student student;

    @Resource
    private NewsLogic newsLogic;


    @RequestMapping("/visit/{id}")
    public String hello(@PathVariable(value = "id") int id) {
        System.out.println("参数为" + id);
        return "success";
    }

    @RequestMapping(value = "/visit")
    @ResponseBody
    public String hello2(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        System.out.println("name为" + name);
        System.out.println("age为" + age);
        return "hehe";
    }

    @RequestMapping(value = "testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String Accept_Language, @RequestHeader(value = "user-agent") String user_agent) {
        System.out.println("testRequestHeader Accept-Languge:" + Accept_Language);
        System.out.println("testRequestHeader user-agent:" + user_agent);
        return "redirect:/visit/2";  //跳转到其他的handler
    }

    /**
     * 接收post请求
     * @param id
     */
    @RequestMapping(value="/addData/{id}", method= RequestMethod.POST)
    public void addData(@PathVariable(value = "id") int id,@RequestBody String code) throws  Exception {
        /***
         * 执行数据操作
         */
        JSONObject bean = JSONObject.fromObject(code);
        System.out.println(bean.get("HEHE"));
        System.out.println("id为"+id);
        System.out.println("数据添加成功");
    }

    @RequestMapping(value="/testResponseBody", method= RequestMethod.POST)
    @ResponseBody
    public Student testResponseBody(HttpServletResponse resp) throws  Exception {
        student.setName("张三");
        student.setId("123456");
        return  student;
    }

    @RequestMapping(value="/DBtest", method= RequestMethod.POST)
    @ResponseBody
    public JSONObject DBtest(){
        return  newsLogic.DbTest();
    }
}
