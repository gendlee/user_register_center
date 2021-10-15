package com.example.controller;

import com.example.convertor.UserRegisterConvertor;
import com.example.entity.UserRegister;
import com.example.enums.RegisterEnum;
import com.example.exception.UserRegisterException;
import com.example.service.MyLogger;
import com.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MysqlController {

    @Autowired
    private MyLogger myLogger;

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public String getId(@RequestParam(value = "id") Long id) {
        return userRegisterService.getUserById(id).toString();
    }


    @RequestMapping(value = "/register")
    public String register() {
        return "mysql_set.html";
    }

    @RequestMapping(value = "/doRegister")
    public String  registerIndex(HttpServletRequest request, HttpServletRequest httpServletRequest) {
        // TODO parameter verify by annotation on field
        String registerResult = RegisterEnum.USER_REGISTER_SUCC.getDesc();
        try {
            UserRegister userRegister = UserRegisterConvertor.buildUserRegister(request);
            userRegisterService.register(userRegister);
        } catch (UserRegisterException e) {
            registerResult = RegisterEnum.USER_REGISTER_FAIL.getDesc();
        }

        httpServletRequest.setAttribute("result", registerResult + "!");
        return "mysql_set.html";
    }

}
