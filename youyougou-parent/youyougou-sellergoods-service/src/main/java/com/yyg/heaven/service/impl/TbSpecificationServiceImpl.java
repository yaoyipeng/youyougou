package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.mapper.TbSpecificationMapper;
import com.yyg.heaven.mapper.TbSpecificationOptionMapper;
import com.yyg.heaven.pojo.TbSpecification;
import com.yyg.heaven.pojo.TbSpecificationOption;
import com.yyg.heaven.service.ITbSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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
public class TbSpecificationServiceImpl extends ServiceImpl<TbSpecificationMapper, TbSpecification> implements ITbSpecificationService {

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;

    @Autowired
    private TbSpecificationOptionMapper tbspecificationOptionMapper;

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<TbSpecification> findAll() {
        return tbSpecificationMapper.selectList(null);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbSpecification> tbSpecifications = tbSpecificationMapper.selectList(null);
        PageInfo pageInfo = new PageInfo(tbSpecifications);
        return pageInfo;
    }

    /**
     * 添加
     * @param specification
     */
    @Override
    public void add(TbSpecification specification) {
        tbSpecificationMapper.insert(specification);    // 插入
        for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){
            specificationOption.setSpecId(specification.getId());//设置规格ID
            tbspecificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    @Override
    public TbSpecification findOne(Long id) {
        // 根据主键id查询一个
        TbSpecification tbSpecification = tbSpecificationMapper.selectById(id);
        // 添加需要查询的外键
        TbSpecificationOption tbSpecificationOption = new TbSpecificationOption();
        tbSpecificationOption.setSpecId(id);
        // 构建条件构造器  把需要外键id查询的条件放入条件构造器中
        QueryWrapper<TbSpecificationOption> queryWrapper = new QueryWrapper<>(tbSpecificationOption);
        // 根据条件构造器查询数据
        List<TbSpecificationOption> tbSpecificationOptions = tbspecificationOptionMapper.selectList(queryWrapper);
        // 将根据外键查询的多条数据封装返回
        tbSpecification.setSpecificationOptionList(tbSpecificationOptions);
        return tbSpecification;
    }

    /**
     * 修改
     * @param specification
     */
    @Override
    public void update(TbSpecification specification) {
        System.out.println(specification);
        // 保存修改的规格
        tbSpecificationMapper.updateById(specification);
        // 根据外键id删除原有的规格选项
        // 构建条件构造器  把需要删除的外键id放入条件构造器中
        TbSpecificationOption tbSpecificationOption = new TbSpecificationOption();
        tbSpecificationOption.setSpecId(specification.getId());
        QueryWrapper<TbSpecificationOption> queryWrapper = new QueryWrapper<>(tbSpecificationOption);
        // 根据外键id删除原有的规格选项
        tbspecificationOptionMapper.delete(queryWrapper);
        // 循环插入规格选项
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            specificationOption.setSpecId(specification.getId());
            // 添加规格选项的数据
            tbspecificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 批量删除(并且级联删除)
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        // 批量删除规格（没有外键约束）
        tbSpecificationMapper.deleteBatchIds(Arrays.asList(ids));
        // 批量删除规格选项
        TbSpecificationOption tbSpecificationOption = new TbSpecificationOption();
        for (Long id : ids) {
            // 构建条件构造器  把需要删除的外键id放入条件构造器中
            tbSpecificationOption.setSpecId(id);
            QueryWrapper<TbSpecificationOption> queryWrapper = new QueryWrapper<>(tbSpecificationOption);
            tbspecificationOptionMapper.delete(queryWrapper);
        }

    }

    /**
     * 根据条件模糊查询
     * @param specification
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageInfo findPageLike(TbSpecification specification, int page, int rows) {
        QueryWrapper<TbSpecification> queryWrapper = new QueryWrapper<>();
        if (specification!=null){
            if (specification.getSpecName()!=null && specification.getSpecName().length()>0){
                queryWrapper.like("spec_name","%"+specification.getSpecName()+"%");
            }
        }
        PageHelper.startPage(page,rows);
        List<TbSpecification> tbSpecifications = tbSpecificationMapper.selectList(queryWrapper);
        PageInfo pageInfo = new PageInfo(tbSpecifications);
        return pageInfo;
    }
    /**
     * 规格下拉列表
     * @return
     */
    @Override
    public List<Map> optionlist() {
        return tbSpecificationMapper.optionlist();
    }


}
