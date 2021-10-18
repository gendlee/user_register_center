package com.example.service;

import com.example.entity.UserRegister;
import com.example.enums.ExceptionEnum;
import com.example.exception.UserRegisterException;
import com.example.mapper.UserRegisterMapper;
import com.example.utils.RedisUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserRegisterService {
    private final static int MAX_LOGIN_NAME_LENGTH = 20;
    private final static int MOBILE_NO_LENGTH = 11;
    private final static int MAX_NICK_NAME_LENGTH = 10;
    private final static int MAX_PWD_LENGTH = 32;
    private final static int MIN_PWD_LENGTH = 8;

    @Resource
    private UserRegisterMapper userRegisterMapper;
    @Autowired
    private MyLogger myLogger;
    @Autowired
    private RedisUtil redisUtil;

    public UserRegister getUserById(Long id) {
        return userRegisterMapper.selectByPrimaryKey(id);
    }


    public void register(UserRegister userRegister) throws UserRegisterException {
        //从缓存读取loginName校验是否已被注册
        Object obj = redisUtil.get(userRegister.getLoginName());

        this.checkRegisterParam(userRegister, obj);

        if (Objects.nonNull(obj)) {
            myLogger.error(ExceptionEnum.USER_LOGIN_NAME_EXIST.getDescription(), userRegister.getLoginName());
            throw new UserRegisterException(ExceptionEnum.USER_LOGIN_NAME_EXIST);
        }

        // todo 改成缓存读取
        if (userRegisterMapper.selectByMobileNo(userRegister.getMobileNo()) != null) {
            myLogger.error(ExceptionEnum.USER_MOBILE_NO_EXIST.getDescription(), userRegister.getMobileNo());
            throw new UserRegisterException(ExceptionEnum.USER_MOBILE_NO_EXIST);
        }

        //数据插入mysql实现注册
        try {
            userRegisterMapper.insertBy4Element(userRegister);
        } catch (Exception e) {
            throw new UserRegisterException(ExceptionEnum.SYSTEM_MYSQL_ERROR);
        }

        //同步将数据插入redis缓存
        try {
            redisUtil.set(userRegister.getLoginName(), Strings.EMPTY);
        } catch (Exception e) {
            myLogger.error(ExceptionEnum.SYSTEM_REDIS_ERROR.getExceptionCode(), e.getMessage());
            //回滚已插入到mysql的数据
            userRegisterMapper.deleteByUniqueKey(userRegister.getLoginName(), userRegister.getMobileNo());
            throw new UserRegisterException(ExceptionEnum.SYSTEM_REDIS_ERROR);
        }
    }

    private void checkRegisterParam(UserRegister userRegister, Object redisObj) throws UserRegisterException {
        if (StringUtils.isEmpty(userRegister.getLoginName()) || StringUtils.length(userRegister.getLoginName()) > MAX_LOGIN_NAME_LENGTH) {
            myLogger.error(ExceptionEnum.SYSTEM_PARAMETER_LOGIN_NAME_ERROR, Strings.EMPTY);
            throw new UserRegisterException(ExceptionEnum.SYSTEM_PARAMETER_LOGIN_NAME_ERROR);
        }
        if (StringUtils.isEmpty(userRegister.getMobileNo()) || StringUtils.length(userRegister.getMobileNo()) != MOBILE_NO_LENGTH) {
            myLogger.error(ExceptionEnum.SYSTEM_PARAMETER_MOBILE_NO_ERROR, Strings.EMPTY);
            throw new UserRegisterException(ExceptionEnum.SYSTEM_PARAMETER_MOBILE_NO_ERROR);
        }
        if (StringUtils.isEmpty(userRegister.getPassword()) || StringUtils.length(userRegister.getPassword()) < MIN_PWD_LENGTH || StringUtils.length(userRegister.getPassword()) > MAX_PWD_LENGTH) {
            myLogger.error(ExceptionEnum.SYSTEM_PARAMETER_PWD_ERROR, Strings.EMPTY);
            throw new UserRegisterException(ExceptionEnum.SYSTEM_PARAMETER_PWD_ERROR);
        }
        if (StringUtils.isEmpty(userRegister.getNickName())) {
            myLogger.error(ExceptionEnum.SYSTEM_PARAMETER_NICK_NAME_ERROR, Strings.EMPTY);
            throw new UserRegisterException(ExceptionEnum.SYSTEM_PARAMETER_NICK_NAME_ERROR);
        }
        if (Objects.nonNull(redisObj)) {
            myLogger.error(ExceptionEnum.USER_LOGIN_NAME_EXIST, userRegister.getLoginName());
            throw new UserRegisterException(ExceptionEnum.USER_LOGIN_NAME_EXIST);
        }
    }

    public List<String> getLoginNameByPage(String loginName, int pageSize) {
        return userRegisterMapper.selectLoginNameByPage(loginName, pageSize);
    }


}
