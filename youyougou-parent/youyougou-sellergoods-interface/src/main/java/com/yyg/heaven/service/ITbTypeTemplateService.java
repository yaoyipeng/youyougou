package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.pojo.TbTypeTemplate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbTypeTemplateService extends IService<TbTypeTemplate> {
    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    PageInfo findPage(int page, int rows);
    /**
     * 根据条件模糊查询
     * @param typeTemplate
     * @param page
     * @param rows
     * @return
     */
    PageInfo findPageLike(TbTypeTemplate typeTemplate, int page, int rows);
}
