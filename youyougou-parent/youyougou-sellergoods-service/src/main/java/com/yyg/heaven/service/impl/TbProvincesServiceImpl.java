package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbProvincesMapper;
import com.yyg.heaven.pojo.TbProvinces;
import com.yyg.heaven.service.ITbProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 省份信息表 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbProvincesServiceImpl extends ServiceImpl<TbProvincesMapper, TbProvinces> implements ITbProvincesService {

    @Autowired
    private TbProvincesMapper tbProvincesMapper;
}
