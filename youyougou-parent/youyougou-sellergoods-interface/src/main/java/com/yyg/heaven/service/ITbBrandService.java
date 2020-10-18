package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbBrand;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbBrandService extends IService<TbBrand> {
    /**
     * 查询全部
     * @return
     */
    List<TbBrand> findAll();
}
