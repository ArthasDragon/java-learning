package com.dragon.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerTest implements ApplicationRunner {

    @Autowired
    public UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setPassword("springboot");
        user.setUsername("dragon");
        userService.save(user);

    }
}
