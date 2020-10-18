package com.yyg.heaven.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbBrandMapper;
import com.yyg.heaven.pojo.TbBrand;
import com.yyg.heaven.service.ITbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements ITbBrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;
    /**
     * 查询全部
     * @return
     */
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectList(null);
    }
}
