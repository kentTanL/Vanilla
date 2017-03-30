package com.newegg.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：这是一个简单的Hello World示例
 */
@RestController
public class HelloController {
    
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    
}
