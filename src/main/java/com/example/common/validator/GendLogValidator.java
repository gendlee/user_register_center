package com.example.common.validator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)  // 数值越小，优先级越高
public class GendLogValidator {

    /**
     * 定义切点
     * 在注解的位置切入代码
     */

    @Pointcut("@annotation(com.example.common.annotation.GendLog)")
    public void logPointCut() {

    }


    @Before("logPointCut()")
    public void before() {
        System.out.println("Step 1-GendLog: 执行前");
    }


    @After("logPointCut()")  // 相当于finally，在AfterReturnIng之后
    public void after() {
        System.out.println("Step 3-1: 方法执行后");
    }

    @AfterThrowing(value = "logPointCut()", throwing = "这是增强中抛出的异常信息")
    public void afterThrowing() {
        System.out.println("Step 4: 方法抛出异常后");
    }

    /**
    *@Desc: Around = Before + After
    */
    // @Around("logPointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("Step X: 环绕执行-方法前处理");
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Step Y: 环绕执行-方法后处理");

        return obj;

    }

    // 在执行注解的方法【后】执行
    /*@AfterReturning("logPointCut()")
    public void printLog(JoinPoint joinPoint) {
        System.out.println("Step 3: 执行后");
        System.out.println("切面方式打印日志");

        // 从切面注入点处通过反射机制获取注入点处的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获取切入点所在的方法
        Method method = methodSignature.getMethod();

        // 获取注解标志
        GendLog gendLog = method.getAnnotation(GendLog.class);

        // 有注解的方法才会执行
        if (Objects.nonNull(gendLog)) {
            String value = gendLog.value();
            // 获取操作名称
            System.out.println("方法名：" + value);

            HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes())
                    .getRequest();


            // 打印操作
            System.out.println("时间: " + new Date());

            System.out.println("路径: " + servletRequest.getContextPath());
            System.out.println("方法: " + servletRequest.getMethod());
            System.out.println("请求url: " + servletRequest.getRequestURI());
            System.out.println("content type: " + servletRequest.getContentType());
            System.out.println("getProtocol: " + servletRequest.getProtocol());
            System.out.println("server port: " + servletRequest.getServerPort());


            System.out.println("打印完成");
        }



    }*/
}
