package com.example.controller;

import com.example.common.annotation.GendLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hi")
    // 通过注解的方式打印日志
    @GendLog(value = "向浏览器用户说hi")
    public String sayHi() {
        return "Hi from gendlee";
    }

}
