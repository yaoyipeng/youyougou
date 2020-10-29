package com.yyg.heaven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyg.heaven.pojo.TbTypeTemplate;

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
public interface TbTypeTemplateMapper extends BaseMapper<TbTypeTemplate> {
    /**
     * 查询类型下拉列表数据
     * @return
     */
    List<Map> selectOptionList();
}
