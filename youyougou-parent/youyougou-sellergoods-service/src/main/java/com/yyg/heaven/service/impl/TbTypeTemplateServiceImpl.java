package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.mapper.TbTypeTemplateMapper;
import com.yyg.heaven.pojo.TbTypeTemplate;
import com.yyg.heaven.service.ITbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class TbTypeTemplateServiceImpl extends ServiceImpl<TbTypeTemplateMapper, TbTypeTemplate> implements ITbTypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

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
//    @Override
//    public List<Map> selectOptionList() {
//        return tbTypeTemplateMapper.selectOptionList();
//    }
}
