package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbGoodsMapper;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.service.ITbGoodsService;
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
public class TbGoodsServiceImpl extends ServiceImpl<TbGoodsMapper, TbGoods> implements ITbGoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;
}
