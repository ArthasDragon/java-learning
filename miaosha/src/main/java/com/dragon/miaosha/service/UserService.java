package com.dragon.miaosha.service;

import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.service.model.UserModel;

public interface UserService {
    //通过用户id获取用户对象的方法
    UserModel getUserById(Integer id);

    //注册
    void register(UserModel userModel) throws BusinessException;

    // 登录验证
    void validateLogin(String telphone, String password);
}
