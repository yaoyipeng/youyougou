package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbPayLogMapper;
import com.yyg.heaven.pojo.TbPayLog;
import com.yyg.heaven.service.ITbPayLogService;
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
public class TbPayLogServiceImpl extends ServiceImpl<TbPayLogMapper, TbPayLog> implements ITbPayLogService {

    @Autowired
    private TbPayLogMapper tbPayLogMapper;
}
