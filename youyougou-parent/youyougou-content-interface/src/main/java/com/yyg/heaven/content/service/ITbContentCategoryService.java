package com.yyg.heaven.content.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbContentCategory;

/**
 * <p>
 * 内容分类 服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbContentCategoryService extends IService<TbContentCategory> {
    /**
     * 根据条件查询
     * @param page
     * @param rows
     * @param tbContentCategory
     * @return
     */
    Page<TbContentCategory> findPageLike(int page, int rows, TbContentCategory tbContentCategory);
}
