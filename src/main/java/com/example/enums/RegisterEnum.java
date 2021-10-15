package com.example.enums;

public enum RegisterEnum {

    USER_REGISTER_SUCC("2000001", "注册成功"),
    USER_REGISTER_FAIL("2000002", "注册失败")

    ;




    private String code;
    private String desc;

    private RegisterEnum(String code, String description) {
        this.code = code;
        this.desc = description;
    }

    public String getExceptionCode() {
        return code;
    }

    public void setExceptionCode(String exceptionCode) {
        this.code = exceptionCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
