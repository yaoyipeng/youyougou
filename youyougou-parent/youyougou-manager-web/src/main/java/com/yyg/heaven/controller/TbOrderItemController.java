package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.service.ITbOrderItemService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/orderItem")
public class TbOrderItemController {

    @Reference
    private ITbOrderItemService tbOrderItemService;

}
