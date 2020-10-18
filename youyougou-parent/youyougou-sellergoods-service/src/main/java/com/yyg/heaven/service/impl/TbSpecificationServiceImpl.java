package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbSpecificationMapper;
import com.yyg.heaven.pojo.TbSpecification;
import com.yyg.heaven.service.ITbSpecificationService;
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
public class TbSpecificationServiceImpl extends ServiceImpl<TbSpecificationMapper, TbSpecification> implements ITbSpecificationService {

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;
}
