package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.pojo.TbItem;

import java.util.List;

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
    /**
     * 根据条件分页查询
     * @param goods
     * @param page
     * @param rows
     * @return
     */
    Page<TbGoods> findPageLike(TbGoods goods, int page, int rows);
    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    TbGoods findOne(Long id);
    /**
     * 修改商品
     * @param tbGoods
     * @return
     */
    void updateTbGoods(TbGoods tbGoods);
    /**
     * 根据商品ID和状态查询Item表信息
     * @param goodsIds
     * @param status
     * @return
     */
    List<TbItem> findItemListByGoodsIdandStatus(Long[] goodsIds, String status );
}
