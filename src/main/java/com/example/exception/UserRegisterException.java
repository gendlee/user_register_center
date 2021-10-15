package com.example.exception;

import com.example.enums.ExceptionEnum;

/**
 * 用户注册类异常
 */
public class UserRegisterException extends Exception{


    public UserRegisterException() {
    }

    public UserRegisterException(String message) {
        super(message);
    }

    public UserRegisterException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getDescription());
    }

    public UserRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRegisterException(Throwable cause) {
        super(cause);
    }

    public UserRegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
