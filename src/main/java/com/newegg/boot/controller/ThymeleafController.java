package com.newegg.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {
    
    @RequestMapping("/thymeleaf/index")
    public String index(ModelMap map) {
        map.addAttribute("host", "www.newegg.com");
        return "thymeleaf/index";
    }
    
}
