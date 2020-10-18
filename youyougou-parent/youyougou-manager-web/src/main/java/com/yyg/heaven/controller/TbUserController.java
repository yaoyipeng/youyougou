package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.service.ITbUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/user")
public class TbUserController {

    @Reference
    private ITbUserService tbUserService;

}
