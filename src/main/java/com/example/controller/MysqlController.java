package com.example.controller;

import com.example.convertor.UserRegisterConvertor;
import com.example.entity.UserRegister;
import com.example.exception.UserRegisterException;
import com.example.service.MyLogger;
import com.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String  registerIndex(HttpServletRequest request) {
        // TODO parameter verify
        try {
            UserRegister userRegister = UserRegisterConvertor.buildUserRegister(request);
            userRegisterService.register(userRegister);
        } catch (UserRegisterException e) {
            return "register_fail.html";
        }

        return "register_succ.html";
    }

}
