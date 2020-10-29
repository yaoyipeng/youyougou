package com.yyg.heaven.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.service.ITbGoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/goods")
public class TbGoodsController {

    @Reference
    private ITbGoodsService tbGoodsService;

    @PostMapping("add")
    public Result add(@RequestBody TbGoods goods){
        System.out.println(goods);
        // 获取登录名
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        // 设置商家ID
        goods.setSellerId(sellerId);
        try {
            tbGoodsService.add(goods);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }

    }

}
