package com.dragon.demo.multiDataSource;

import com.dragon.demo.multiDataSource.first.UserOne;
import com.dragon.demo.multiDataSource.first.UserOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSourceTest implements ApplicationRunner {
    @Autowired
    public UserOneService userOneService;

    /**
     * 该方法再SpringBoot启动完成后立即执行
     */
    @Override
    public void run(ApplicationArguments args) {
//        新建一个实体类
        UserOne userOne = new UserOne();
        userOne.setUsername("shiyanlou1");
        userOne.setPassword("springboot1");
//        调用Service
        userOneService.save(userOne);

    }
}
