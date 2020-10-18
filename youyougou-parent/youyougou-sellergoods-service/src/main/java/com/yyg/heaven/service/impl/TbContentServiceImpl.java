package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbContentMapper;
import com.yyg.heaven.pojo.TbContent;
import com.yyg.heaven.service.ITbContentService;
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
public class TbContentServiceImpl extends ServiceImpl<TbContentMapper, TbContent> implements ITbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
}
