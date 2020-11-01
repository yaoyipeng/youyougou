package com.yyg.heaven.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.content.service.ITbContentCategoryService;
import com.yyg.heaven.mapper.TbContentCategoryMapper;
import com.yyg.heaven.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbContentCategoryServiceImpl extends ServiceImpl<TbContentCategoryMapper, TbContentCategory> implements ITbContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    /**
     * 根据条件查询
     * @param page
     * @param rows
     * @param tbContentCategory
     * @return
     */
    @Override
    public Page<TbContentCategory> findPageLike(int page, int rows, TbContentCategory tbContentCategory) {
        QueryWrapper<TbContentCategory> queryWrapper = new QueryWrapper<>();
        if (tbContentCategory!=null){
            if (tbContentCategory.getName()!=null && tbContentCategory.getName().length()>0){
                queryWrapper.like("name","%"+tbContentCategory.getName()+"%");
            }
        }
        Page<TbContentCategory> tbContentCategoryPage = new Page<>(page, rows);
        return tbContentCategoryMapper.selectPage(tbContentCategoryPage,queryWrapper);
    }
}
