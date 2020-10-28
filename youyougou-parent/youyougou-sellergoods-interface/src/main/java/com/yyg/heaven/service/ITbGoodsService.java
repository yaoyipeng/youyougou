package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbGoods;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbGoodsService extends IService<TbGoods> {

    /**
     * 增加
     */
    void add(TbGoods goods);
}
