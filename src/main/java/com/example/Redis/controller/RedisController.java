package com.example.Redis.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RedisController {


    @GetMapping("/hello")
    public ModelAndView hello() {
        return new ModelAndView("/redis.html");
    }

    @RequestMapping("/set")
    public ModelAndView setRedis(@RequestParam(value = "redisKey", defaultValue = "") String redisKey, @RequestParam(value = "redisValue", defaultValue = "") String redisValue) {
        System.out.println(redisKey + " : " + redisValue);

        return new ModelAndView("/redis");
    }


}
