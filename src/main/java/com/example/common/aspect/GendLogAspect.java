package com.example.common.aspect;

import com.example.common.annotation.GendLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class GendLogAspect {

    /**
     * 定义切点
     * 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.example.common.annotation.GendLog)")
    public void logPointCut() {

    }

    /**
     * 切面
     * @param joinPoint
     */
    @AfterReturning("logPointCut()")
    public void printLog(JoinPoint joinPoint) {

        System.out.println("切面方式打印日志");

        // 从切面注入点处通过反射机制获取注入点处的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获取切入点所在的方法
        Method method = methodSignature.getMethod();

        // 获取注解标志
        GendLog gendLog = method.getAnnotation(GendLog.class);
        if (Objects.nonNull(gendLog)) {
            String value = gendLog.value();
            // 获取操作名称
            System.out.println("方法名：" + value);
        }

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();


        // 打印操作
        System.out.println("时间: " + new Date());

        System.out.println("路径: " + servletRequest.getPathInfo());
        System.out.println("方法: " + servletRequest.getMethod());
        System.out.println("请求url: " + servletRequest.getRequestURI());
        System.out.println("content type: " + servletRequest.getContentType());
        System.out.println("getProtocol: " + servletRequest.getProtocol());
        System.out.println("server port: " + servletRequest.getServerPort());


        System.out.println("打印完成");



    }
}
