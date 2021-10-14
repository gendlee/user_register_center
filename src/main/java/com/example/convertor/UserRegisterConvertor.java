package com.example.convertor;

import com.example.Security.MD5;
import com.example.entity.UserRegister;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.Random;
import java.util.UUID;

public class UserRegisterConvertor {

    private static final String STATUS_A = "A";  //激活状态
    private static final String STATUS_D = "D";  //注销状态
    private static final String STATUS_P = "P";  //审核状态

    private static final String ROLE_U = "user";  //普通用户
    private static final String ROLE_M = "manager";  //管理员
    private static final String ROLE_S = "super";  //超级管理员



    public static UserRegister buildUserRegister(HttpServletRequest request) {
        UserRegister userRegister = new UserRegister();

        //随机生成ID
        String genUserId = UUID.randomUUID().toString().substring(0, 32);
        userRegister.setUserId(genUserId);

        //对password加密
        String pwdDecode = MD5.getDecode(request.getParameter("password"));
        userRegister.setPassword(pwdDecode);

        userRegister.setLoginName(request.getParameter("loginName"));
        userRegister.setNickName(request.getParameter("nickName"));
        userRegister.setMobileNo(request.getParameter("mobileNo"));
        userRegister.setActiveStatus(STATUS_A);
        userRegister.setRole(ROLE_U);

        return userRegister;
    }
}
