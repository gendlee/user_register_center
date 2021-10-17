package com.example.service;

import com.example.utils.RedisUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 缓存预热
 */
@Service
public class CachePreheatService implements InitializingBean {
    private static final int PAGE_SIZE = 500;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserRegisterService userRegisterService;
    
    @Override
    public void afterPropertiesSet() {
        String loginName = Strings.EMPTY;
        List<String> currentLoginNames = userRegisterService.getLoginNameByPage(loginName, PAGE_SIZE);
        while ( ! CollectionUtils.isEmpty(currentLoginNames)) {
            for (String str : currentLoginNames) {
                redisUtil.set(str, Strings.EMPTY);
            }
            loginName = currentLoginNames.get(currentLoginNames.size() - 1);
            currentLoginNames = userRegisterService.getLoginNameByPage(loginName, PAGE_SIZE);
        }
    }
}
