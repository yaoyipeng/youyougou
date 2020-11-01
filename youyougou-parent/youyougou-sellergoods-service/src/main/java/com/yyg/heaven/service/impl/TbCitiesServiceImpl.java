package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbCitiesMapper;
import com.yyg.heaven.pojo.TbCities;
import com.yyg.heaven.service.ITbCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 行政区域地州市信息表 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbCitiesServiceImpl extends ServiceImpl<TbCitiesMapper, TbCities> implements ITbCitiesService {

    @Autowired
    private TbCitiesMapper tbCitiesMapper;
}
