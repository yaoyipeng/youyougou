package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbContentCategoryMapper;
import com.yyg.heaven.pojo.TbContentCategory;
import com.yyg.heaven.service.ITbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
public class TbContentCategoryServiceImpl extends ServiceImpl<TbContentCategoryMapper, TbContentCategory> implements ITbContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
}
