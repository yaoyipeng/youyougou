package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbGoodsDescMapper;
import com.yyg.heaven.mapper.TbGoodsMapper;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.pojo.TbGoodsDesc;
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

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;
    /**
     * 增加
     */
    @Override
    public void add(TbGoods goods) {

        // 执行添加
        tbGoodsMapper.insert(goods);
        // 设置id（TbGoods中主键赋值到GoodsDesc中主键）
        TbGoodsDesc goodsDesc = goods.getGoodsDesc();
        goodsDesc.setGoodsId(goods.getId());
        // 插入商品扩展数据
        tbGoodsDescMapper.insert(goodsDesc);
    }
}
