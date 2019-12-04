package com.dragon.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.dragon.miaosha.controller.viewobject.UserVO;
import com.dragon.miaosha.error.BusinessException;
import com.dragon.miaosha.error.EmBusinessError;
import com.dragon.miaosha.response.CommonReturnType;
import com.dragon.miaosha.service.UserService;
import com.dragon.miaosha.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    //用户注册接口
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Byte gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //验证手机号和对应的otpCode相符合
        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(inSessionOtpCode,otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }

        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(gender);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(EncodeByMd5(password));

        userService.register(userModel);

        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定一个计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));

        return newstr;
    }

    //用户获取otp短信接口
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
        //需要按照一定的规则生成opt验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);

        //将OTP验证码同用户的手机号关联，使用httpsession方式绑定optCode和手机号
        httpServletRequest.getSession().setAttribute(telphone, optCode);

        //将otp验证码通过短信通道发送给用户，省略
        System.out.println("telohone = " + telphone + " & optCode = " + optCode);


        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        // 若获取的对应用户信息不存在
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIT);
        }

        //将核心领域模型对象转化为可供ui使用的object
        UserVO userVO = convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();

        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
