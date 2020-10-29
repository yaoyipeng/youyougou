package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.mapper.TbSpecificationOptionMapper;
import com.yyg.heaven.mapper.TbTypeTemplateMapper;
import com.yyg.heaven.pojo.TbSpecificationOption;
import com.yyg.heaven.pojo.TbTypeTemplate;
import com.yyg.heaven.service.ITbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
public class TbTypeTemplateServiceImpl extends ServiceImpl<TbTypeTemplateMapper, TbTypeTemplate> implements ITbTypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageInfo findPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TbTypeTemplate> tbTypeTemplates = tbTypeTemplateMapper.selectList(null);
        PageInfo pageInfo = new PageInfo(tbTypeTemplates);
        return pageInfo;
    }
    /**
     * 根据条件模糊查询
     * @param typeTemplate
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageInfo findPageLike(TbTypeTemplate typeTemplate, int page, int rows) {
        QueryWrapper<TbTypeTemplate> queryWrapper = new QueryWrapper<>();
        if (typeTemplate!=null){
            if (typeTemplate.getName()!=null && typeTemplate.getName().length()>0){
                queryWrapper.like("name","%"+typeTemplate.getName()+"%");
            }
        }
        PageHelper.startPage(page,rows);
        List<TbTypeTemplate> tbTypeTemplates = tbTypeTemplateMapper.selectList(queryWrapper);
        PageInfo pageInfo = new PageInfo(tbTypeTemplates);
        return pageInfo;
    }
    /**
     * 查询类型下拉列表数据
     * @return
     */
    @Override
    public List<Map> selectOptionList() {
        return tbTypeTemplateMapper.selectOptionList();
    }
    /**
     * 返回规格列表tbSpecificationOptionMapper
     * @param id
     * @return
     */
    @Override
    public List<Map> findSpecList(Long id) {
        // 查询模板（根据id查询）
        TbTypeTemplate tbTypeTemplate = tbTypeTemplateMapper.selectById(id);
        // 拿到规格数据，specIds的集合（JSON），封装成list集合
        List<Map> list = JSON.parseArray(tbTypeTemplate.getSpecIds(), Map.class);
        // 遍历规格数据
        for (Map map : list) {
            // 查询规格选项列表
            QueryWrapper<TbSpecificationOption> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("spec_id", new Long((Integer) map.get("id")));
            // 根据spec_id查询规格表
            List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectList(queryWrapper);
            map.put("options",tbSpecificationOptions);
        }
        return list;
    }
}
