package com.example.controller;

import com.example.common.annotation.GendLog;
import com.example.common.annotation.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hi")
    // 通过注解的方式打印日志
    @GendLog(value = "向浏览器用户说hi")
    public String sayHi() throws Exception {
        System.out.println("Step 2: 执行中");

        //throw new Exception("nothing");

         return "Hi from gendlee";
    }

    @GetMapping("/read")
    @Permission(rule = "USER", ruleCode = 4)
    public String userRead(@RequestParam(value = "userRule", defaultValue = "") String userRule) {
        System.out.println("执行中...");
        return "you have access to read!";
    }

    @GetMapping("/adminRead")
    @Permission(rule = "ADMIN", ruleCode = 2)
    public String adminRead(@RequestParam(value = "userRule", defaultValue = "") String userRule) {
        return "you have access to read!";
    }
}
