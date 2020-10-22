package com.yyg.heaven.mapper;

import com.yyg.heaven.pojo.TbBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface TbBrandMapper extends BaseMapper<TbBrand> {
    /**
     * 查询品牌下拉列表数据
     * @return
     */
    List<Map> selectOptionList();
}
