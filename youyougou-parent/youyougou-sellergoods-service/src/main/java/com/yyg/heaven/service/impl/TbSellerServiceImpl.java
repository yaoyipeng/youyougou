package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbSellerMapper;
import com.yyg.heaven.pojo.TbSeller;
import com.yyg.heaven.service.ITbSellerService;
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
public class TbSellerServiceImpl extends ServiceImpl<TbSellerMapper, TbSeller> implements ITbSellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;
}
