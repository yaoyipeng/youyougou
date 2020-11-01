package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbGoodsDescMapper;
import com.yyg.heaven.pojo.TbGoodsDesc;
import com.yyg.heaven.service.ITbGoodsDescService;
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
public class TbGoodsDescServiceImpl extends ServiceImpl<TbGoodsDescMapper, TbGoodsDesc> implements ITbGoodsDescService {

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;
}
