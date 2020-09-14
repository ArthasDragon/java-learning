package com.dragon.miaosha.response;

public class CommonReturnType {
    // 表明对应请求的处理结果  有success和fail
    private String status;

    // 若status=success，则data返回前端需要的json数据
    // 若status=fail，则data使用公用的错误码格式
    private Object data;

    // 定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result,String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(result);
        commonReturnType.setStatus(status);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
