package com.example.convertor;

import com.example.Security.MD5;
import com.example.entity.UserRegister;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.*;

public class UserRegisterConvertor {

    private static final String STATUS_A = "A";  //激活状态
    private static final String STATUS_D = "D";  //注销状态
    private static final String STATUS_P = "P";  //审核状态

    private static final String ROLE_U = "user";  //普通用户
    private static final String ROLE_M = "manager";  //管理员
    private static final String ROLE_S = "super";  //超级管理员



    public static UserRegister buildUserRegister(HttpServletRequest request) {

        return buildUserRegister(request.getParameter("loginName"),
                                 request.getParameter("password"),
                                 request.getParameter("nickName"),
                                 request.getParameter("mobileNo"));
    }


    public static UserRegister buildUserRegister(String loginName, String pwd, String nickName, String mobileNo) {
        UserRegister userRegister = new UserRegister();

        //随机生成ID
        String genUserId = UUID.randomUUID().toString().substring(0, 32);
        userRegister.setUserId(genUserId);

        //对password加密 todo 先对pwd格式进行校验
        String pwdDecode = MD5.getDecode(pwd);
        userRegister.setPassword(pwdDecode);

        userRegister.setLoginName(loginName);
        userRegister.setNickName(nickName);
        userRegister.setMobileNo(mobileNo);
        userRegister.setActiveStatus(STATUS_A);
        userRegister.setRole(ROLE_U);

        return userRegister;
    }
}
