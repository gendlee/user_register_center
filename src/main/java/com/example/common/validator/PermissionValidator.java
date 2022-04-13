package com.example.common.validator;

import com.example.common.annotation.Permission;
import com.example.enums.ExceptionEnum;
import com.example.exception.UserRegisterException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

/**
 * 权限控制 父类
 */
@Aspect
@Component
public class PermissionValidator {
    // 角色
    private static final int SUPER_ADMIN = 1;
    private static final int ADMIN = 2;
    private static final int USER = 4;

    // 执行耗时
    private static long startTimeMilli;
    private static long endTimeMilli;

    // 切点
    @Pointcut("@annotation(com.example.common.annotation.Permission)")
    public void pointCut() {

    }

    // 执行方法前
    @Before("pointCut()")
    public void surfaceCut(JoinPoint joinPoint) throws UserRegisterException{
        System.out.println("进入权限判断");
        startTimeMilli = Instant.now().toEpochMilli();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Permission permission = method.getAnnotation(Permission.class);

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();

        if (Objects.nonNull(permission)) {
            String rule = permission.rule();
            System.out.println("角色名称为：" + rule);

            int ruleCode = permission.ruleCode();

            switch (ruleCode) {
                case SUPER_ADMIN:
                    System.out.println("权限为超级管理员：" + ruleCode);
                    break;
                case ADMIN:
                    System.out.println("权限为管理员：" + ruleCode);
                    break;
                case USER:
                    System.out.println("权限为普通用户：" + ruleCode);
                    String requestUserRole = servletRequest.getParameter("userRole");
                    if ("USER".equals(requestUserRole)) {
                        System.out.println("请求用户权限校验：" + requestUserRole + " 一致");
                    } else {
                        System.out.println("请求用户权限校验：" + requestUserRole + " 不一致");
                        throw new UserRegisterException(ExceptionEnum.HAS_NO_USER_PERMISSION);
                    }
                    break;
                default:
                    System.out.println("无权限用户：" + ruleCode);
                    throw new UserRegisterException(ExceptionEnum.HAS_NO_PERMISSION);
            }

        }


    }

    // 执行方法后
    @After("pointCut()")
    public void afterProcess() {
        endTimeMilli = Instant.now().toEpochMilli();
        long elapsedTime = endTimeMilli - startTimeMilli;
        System.out.println("总耗时（毫秒）：" + elapsedTime);

    }


}
