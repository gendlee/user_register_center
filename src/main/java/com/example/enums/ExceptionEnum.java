package com.example.enums;

public enum ExceptionEnum {

    USER_LOGIN_NAME_EXIST("4000001", "用户名已被注册"),
    USER_MOBILE_NO_EXIST("4000002", "手机号已被注册"),

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
