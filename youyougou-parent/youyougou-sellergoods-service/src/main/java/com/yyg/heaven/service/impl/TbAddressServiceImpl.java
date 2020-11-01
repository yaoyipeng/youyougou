package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbAddressMapper;
import com.yyg.heaven.pojo.TbAddress;
import com.yyg.heaven.service.ITbAddressService;
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
public class TbAddressServiceImpl extends ServiceImpl<TbAddressMapper, TbAddress> implements ITbAddressService {

    @Autowired
    private TbAddressMapper tbAddressMapper;

}
