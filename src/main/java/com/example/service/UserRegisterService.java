package com.example.service;

import com.example.entity.UserRegister;
import com.example.mapper.UserRegisterMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
public class UserRegisterService {

    @Resource
    private UserRegisterMapper userRegisterMapper;

    public UserRegister getUserById(Long id) {
        return userRegisterMapper.selectByPrimaryKey(id);
    }


    public int insertUser(UserRegister userRegister) {
        return userRegisterMapper.insertBy4Element(userRegister);
    }
}
