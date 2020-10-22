package com.yyg.heaven.mapper;

import com.yyg.heaven.pojo.TbSpecification;
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
public interface TbSpecificationMapper extends BaseMapper<TbSpecification> {
    /**
     * 规格下拉列表
     * @return
     */
    List<Map> optionlist();
}
