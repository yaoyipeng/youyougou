package com.yyg.heaven.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbContent;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbContentService extends IService<TbContent> {
    /**
     * 根据广告分类ID查询广告列表
     * @param categoryId
     * @return
     */
    List<TbContent> findByCategoryId(Long categoryId);
}
