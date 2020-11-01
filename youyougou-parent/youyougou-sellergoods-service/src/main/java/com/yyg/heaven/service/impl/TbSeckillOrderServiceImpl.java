package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbSeckillOrderMapper;
import com.yyg.heaven.pojo.TbSeckillOrder;
import com.yyg.heaven.service.ITbSeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbSeckillOrderServiceImpl extends ServiceImpl<TbSeckillOrderMapper, TbSeckillOrder> implements ITbSeckillOrderService {

    @Autowired
    private TbSeckillOrderMapper tbSeckillOrderMapper;
}
