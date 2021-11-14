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

import javax.jws.soap.SOAPBinding;
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
        return "register.html";
    }

    @RequestMapping(value = "/multiRegister")
    public String multiRegister() {
        return "multi_register.html";
    }

    @RequestMapping(value = "/doRegister")
    public String  doRegister(HttpServletRequest request) {
        // TODO parameter verify by annotation on field
        String registerResult = RegisterEnum.USER_REGISTER_SUCC.getDesc();
        try {
            UserRegister userRegister = UserRegisterConvertor.buildUserRegister(request);
            userRegisterService.register(userRegister, -1, 0);
        } catch (UserRegisterException e) {
            registerResult = RegisterEnum.USER_REGISTER_FAIL.getDesc();
        }

        request.setAttribute("result", registerResult + "!");
        return "register.html";
    }

    //todo 仅用于展示批量注册用户产生的 <缓存雪崩> 效果
    @RequestMapping(value = "/doMultiRegister")
    public String doMultiRegister(HttpServletRequest request) {
        String[] loginNames = request.getParameter("loginNames").split(",");
        int i = 0;
        String registerResult = RegisterEnum.USER_REGISTER_SUCC.getDesc();
        for (String loginName : loginNames) {
            UserRegister userRegister = UserRegisterConvertor.buildUserRegister(loginName,"123@abc", "nickName" + i, "1806666888" + i);
            try {
                int cacheExpireTime = 20;
                userRegisterService.register(userRegister, cacheExpireTime, 15);
            } catch (UserRegisterException ure) {
                myLogger.error("批量注册有异常：" + ure.getMessage());
            }
            i++;
        }

        request.setAttribute("result", registerResult);
        return "multi_register.html";

    }

}
