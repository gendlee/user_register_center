package com.example.Redis.controller;

import com.example.Redis.service.MyLogger;
import com.example.Redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RedisController {

    @Autowired
    private MyLogger myLogger;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/hello")
    public ModelAndView hello() {
        return new ModelAndView("/redis.html");
    }

    @RequestMapping("/set")
    public ModelAndView setRedis(@RequestParam(value = "redisKey", defaultValue = "") String redisKey, @RequestParam(value = "redisValue", defaultValue = "") String redisValue) {
        try {
            redisUtil.set(redisKey, redisValue);
            myLogger.info("redis写数据："+ redisKey + "  " + redisValue);
        } catch (Exception e) {
            myLogger.error("redis写数据失败："+ redisKey + "  " + redisValue);
        }

        return new ModelAndView("/redis");
    }

    @RequestMapping("/get")
    public String getRedis(@RequestParam(value = "redisKey", defaultValue = "") String redisKey) {
        String redisValue = null;
        try {
            redisValue = (String)redisUtil.get(redisKey);
            myLogger.info("redis读数据："+ redisKey + "  " + redisValue);
        } catch (Exception e) {
            myLogger.error("redis读数据失败："+ redisKey);
        }
        if (redisValue != null) {
            return redisValue;
        }

        return "fail to read";
    }




}
