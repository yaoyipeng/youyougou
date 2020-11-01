package com.yyg.heaven.portal.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.content.service.ITbContentService;
import com.yyg.heaven.pojo.TbContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/content")
public class TbContentController {

    @Reference
    private ITbContentService tbContentService;

    /**
     * 根据广告分类ID查询广告列表
     * @param categoryId
     * @return
     */
    @GetMapping("/findByCategoryId")
    public List<TbContent> findByCategoryId(Long categoryId) {
        return tbContentService.findByCategoryId(categoryId);
    }
}
