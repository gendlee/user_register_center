package com.example.controller;

import com.example.convertor.UserRegisterConvertor;
import com.example.entity.UserRegister;
import com.example.service.MyLogger;
import com.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MysqlController {

    @Autowired
    private MyLogger myLogger;

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public String getId(@RequestParam(value = "id") Long id) {
        return userRegisterService.getUserById(id).toString();
    }

    /**
     * 用户注册
     * @param loginName
     * @param password
     * @param nickName
     * @param mobileNo
     * @return
     */
    @RequestMapping(value = "/register")
    public ModelAndView register(@RequestParam(value = "loginName") String loginName,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "nickName") String nickName,
                              @RequestParam(value = "mobileNo") String mobileNo) {
        try {
            UserRegister userRegister = UserRegisterConvertor.buildUserRegister(loginName,password, nickName, mobileNo);
            userRegisterService.insertUser(userRegister);
        } catch (Exception e) {
            myLogger.error("注册失败, 原因是：" + e);
            return new ModelAndView("/register_fail.html");
        }

        return new ModelAndView("/register_succ.html");
    }

}
