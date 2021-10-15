package com.example.service;

import com.example.entity.UserRegister;
import com.example.enums.ExceptionEnum;
import com.example.exception.UserRegisterException;
import com.example.mapper.UserRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRegisterService {

    @Resource
    private UserRegisterMapper userRegisterMapper;
    @Autowired
    private MyLogger myLogger;

    public UserRegister getUserById(Long id) {
        return userRegisterMapper.selectByPrimaryKey(id);
    }


    public void register(UserRegister userRegister) throws UserRegisterException{
        UserRegister user = null;
        user = userRegisterMapper.selectByLoginName(userRegister.getLoginName());
        if (user != null) {
            myLogger.error(ExceptionEnum.USER_LOGIN_NAME_EXIST.getDescription(), userRegister.getLoginName());
            throw new UserRegisterException(ExceptionEnum.USER_LOGIN_NAME_EXIST);
        }
        user = userRegisterMapper.selectByMobileNo(userRegister.getMobileNo());
        if (user != null) {
            myLogger.error(ExceptionEnum.USER_MOBILE_NO_EXIST.getDescription(), userRegister.getMobileNo());
            throw new UserRegisterException(ExceptionEnum.USER_MOBILE_NO_EXIST);
        }

        userRegisterMapper.insertBy4Element(userRegister);
    }

}
