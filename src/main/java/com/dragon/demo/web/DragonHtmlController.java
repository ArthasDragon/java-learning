package com.dragon.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DragonHtmlController {

    @RequestMapping("dragon")
    public String dragon(){
        return "dragon";
    }
}
