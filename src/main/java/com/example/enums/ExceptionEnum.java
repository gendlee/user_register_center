package com.example.enums;

public enum ExceptionEnum {

    USER_LOGIN_NAME_EXIST("4000001", "用户名已被注册"),
    USER_MOBILE_NO_EXIST("4000002", "手机号已被注册"),

    SYSTEM_REDIS_ERROR("4000020", "redis存取异常"),
    SYSTEM_MYSQL_ERROR("4000021", "mysql存取异常"),
    SYSTEM_PARAMETER_ERROR("4000022", "参数异常"),
    SYSTEM_PARAMETER_LOGIN_NAME_ERROR("4000023", "登录名格式异常"),
    SYSTEM_PARAMETER_MOBILE_NO_ERROR("4000024", "手机号格式异常"),
    SYSTEM_PARAMETER_PWD_ERROR("4000025", "密码格式异常"),
    SYSTEM_PARAMETER_NICK_NAME_ERROR("4000026", "昵称格式异常"),

    HAS_NO_SUPER_ADMIN_PERMISSION("4100002", "没有超级管理员权限"),
    HAS_NO_ADMIN_PERMISSION("4100001", "没有管理员权限"),
    HAS_NO_USER_PERMISSION("4100003", "没有普通用户权限"),
    HAS_NO_PERMISSION("4100004", "无权限"),


    ;




    private String exceptionCode;
    private String description;

    private ExceptionEnum(String exceptionCode, String description) {
        this.exceptionCode = exceptionCode;
        this.description = description;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
