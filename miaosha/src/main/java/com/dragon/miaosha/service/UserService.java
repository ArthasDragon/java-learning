package com.dragon.miaosha.service;

import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.service.model.UserModel;

public interface UserService {
    //通过用户id获取用户对象的方法
    UserModel getUserById(Integer id);

    //注册
    void register(UserModel userModel) throws BusinessException;

    // 登录验证
    /**
     * telphone:用户手机
     * password:用户加密后的密码
     * */
    UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;
}
