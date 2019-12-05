package com.dragon.miaosha.error;

public enum EmBusinessError implements CommonError {
    //通用错误类型1000
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKOWN_ERROR(10002, "未知错误"),

    //2000开头为用户信息相关错误定义
    USER_NOT_EXIT(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户手机号或密码不正确")

    ;

    private EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;


    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
