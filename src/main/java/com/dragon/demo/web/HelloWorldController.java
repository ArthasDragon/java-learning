package com.dragon.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot HelloWorld 案例
 *
 * Created by bysocket on 16/4/26.
 */
@RestController
@PropertySource(value = "classpath:dragon.properties")
public class HelloWorldController {

    @Value("${dragon.test}")
    private String dragon;

    @RequestMapping("/aaa")
    public String sayHello() {
        return dragon;
    }
}
