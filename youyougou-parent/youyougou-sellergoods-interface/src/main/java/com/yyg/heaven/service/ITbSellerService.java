package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbSeller;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbSellerService extends IService<TbSeller> {
    /**
     * 根据条件分页模糊查询
     * @param page
     * @param rows
     * @param tbSeller
     * @return
     */
    Page<TbSeller> findPageLike(int page, int rows, TbSeller tbSeller);


}
