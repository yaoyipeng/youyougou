package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbSeckillGoodsMapper;
import com.yyg.heaven.pojo.TbSeckillGoods;
import com.yyg.heaven.service.ITbSeckillGoodsService;
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
public class TbSeckillGoodsServiceImpl extends ServiceImpl<TbSeckillGoodsMapper, TbSeckillGoods> implements ITbSeckillGoodsService {

    @Autowired
    private TbSeckillGoodsMapper tbSeckillGoodsMapper;
}
