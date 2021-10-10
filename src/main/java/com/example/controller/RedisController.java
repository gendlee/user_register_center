package com.example.controller;

import com.example.service.MyLogger;
import com.example.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RedisController {

    @Autowired
    private MyLogger myLogger;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.redis.expire}")
    private long EXPIRE_TIME_SECOND;

    @GetMapping("/hello")
    public ModelAndView hello() {
        return new ModelAndView("/redis_set.html");
    }

    @RequestMapping("/set")
    public ModelAndView setRedis(@RequestParam(value = "redisKey", defaultValue = "key") String redisKey,
                                 @RequestParam(value = "redisValue", defaultValue = "value") String redisValue,
                                 @RequestParam(value = "expireTime", defaultValue = "1000") String expireTime) {
        try {
            redisUtil.set(redisKey, redisValue, Long.parseLong(expireTime));
            myLogger.info("redis写数据："+ redisKey + " -> "  + redisValue, "过期时间： " + expireTime + "秒");
        } catch (Exception e) {
            myLogger.error("redis写数据失败："+ redisKey + " -> "  + redisValue);
        }

        return new ModelAndView("/redis_set.html");
    }

    @RequestMapping("/get")
    public ModelAndView getRedis(@RequestParam(value = "redisKey", defaultValue = "") String redisKey,
                                 HttpServletRequest httpServletRequest) {
        String redisValue = null;
        try {
            redisValue = (String)redisUtil.get(redisKey);
            myLogger.info("redis读数据："+ redisKey + " -> " + redisValue);
        } catch (Exception e) {
            myLogger.error("redis读数据失败："+ redisKey);
        }
        //设置html中页面参数值
        httpServletRequest.setAttribute("redisValue", redisValue);
        return new ModelAndView("/redis_get.html");
    }




}
