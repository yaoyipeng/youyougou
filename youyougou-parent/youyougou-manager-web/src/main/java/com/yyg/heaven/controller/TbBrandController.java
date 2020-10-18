package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.pojo.TbBrand;
import com.yyg.heaven.service.ITbBrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * 品牌controller
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/brand")
public class TbBrandController {

    //理解成远程注入
    @Reference
    private ITbBrandService tbBrandService;

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return tbBrandService.findAll();
    }

}
