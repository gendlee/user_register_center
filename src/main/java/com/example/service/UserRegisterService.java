package com.example.service;

import com.example.entity.UserRegister;
import com.example.mapper.UserRegisterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRegisterService {

    @Resource
    private UserRegisterMapper userRegisterMapper;

    public UserRegister getUserById(Long id) {
        return userRegisterMapper.selectByPrimaryKey(id);
    }

}
