package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbAreasMapper;
import com.yyg.heaven.pojo.TbAreas;
import com.yyg.heaven.service.ITbAreasService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 行政区域县区信息表 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
public class TbAreasServiceImpl extends ServiceImpl<TbAreasMapper, TbAreas> implements ITbAreasService {

    @Autowired
    private TbAreasMapper tbAreasMapper;
}
