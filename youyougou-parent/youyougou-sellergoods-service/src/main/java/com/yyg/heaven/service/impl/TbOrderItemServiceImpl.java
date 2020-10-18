package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbOrderItemMapper;
import com.yyg.heaven.pojo.TbOrderItem;
import com.yyg.heaven.service.ITbOrderItemService;
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
public class TbOrderItemServiceImpl extends ServiceImpl<TbOrderItemMapper, TbOrderItem> implements ITbOrderItemService {

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
}
