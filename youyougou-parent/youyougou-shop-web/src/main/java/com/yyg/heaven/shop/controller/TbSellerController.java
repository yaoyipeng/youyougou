package com.yyg.heaven.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbSeller;
import com.yyg.heaven.service.ITbSellerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/seller")
public class TbSellerController {

    @Reference
    private ITbSellerService tbSellerService;

    /**
     * 增加
     * @param seller
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody TbSeller seller){
        //密码加密
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(seller.getPassword());//加密
        seller.setPassword(password);
        seller.setStatus("0");
        seller.setCreateTime(new Date());
        try {
            tbSellerService.save(seller);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
}
