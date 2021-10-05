package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/abc")
public class abc {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求数据
        System.out.println(request.getMethod());
        Enumeration<String> enumeration= request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name= enumeration.nextElement();
            String value= request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(request.getParameter("code"));

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer= response.getWriter();
        writer.write("<h1>牛客网</h1>");
    }


    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public  String getStudents(
            @RequestParam(name="current",required =false,defaultValue = "1") int current,
            @RequestParam(name="limit",required =false,defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);

        return  "some student！";
    }
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public  String getStudent(
            @PathVariable("id") int id ){
        System.out.println(id);
        return  "a student！";
    }
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public  String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return  "success！";
    }
    @RequestMapping(path="/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav= new ModelAndView();
        mav.addObject("name","李四");
        mav.addObject("age",30);
        mav.setViewName("demo/view");
        return mav;
    }
    @RequestMapping(path="/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000);
        return  emp;
    }

}
