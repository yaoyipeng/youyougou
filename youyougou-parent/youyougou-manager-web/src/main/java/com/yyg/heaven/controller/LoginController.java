package com.yyg.heaven.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: youyougou-parent
 * @description     登录控制层
 * @author: 影耀子（YingYew）
 * @create: 2020-10-23 16:01
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("name")
    public Map name(){
        String name= SecurityContextHolder.getContext()
                .getAuthentication().getName();
        Map map=new HashMap();
        map.put("loginName", name);
        return map ;
    }
}
