package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbTypeTemplateMapper;
import com.yyg.heaven.pojo.TbTypeTemplate;
import com.yyg.heaven.service.ITbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
public class TbTypeTemplateServiceImpl extends ServiceImpl<TbTypeTemplateMapper, TbTypeTemplate> implements ITbTypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;
}
