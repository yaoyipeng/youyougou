package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.mapper.TbBrandMapper;
import com.yyg.heaven.pojo.TbBrand;
import com.yyg.heaven.service.ITbBrandService;
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
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements ITbBrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;
    /**
     * 查询全部
     * @return
     */
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectList(null);
    }

    /**
     * 分页查询数据
     * @param page  当前页
     * @param size  每页显示页数
     * @return
     */
    @Override
    public PageInfo findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<TbBrand> tbBrands = tbBrandMapper.selectList(null);
        PageInfo pageInfo = new PageInfo(tbBrands);
        return pageInfo;
    }
    /**
     * 增加
     */
    @Override
    public void add(TbBrand brand) {
        tbBrandMapper.insert(brand);
    }

    /**
     * 修改
     * @param brand
     */
    @Override
    public void update(TbBrand brand) {
        tbBrandMapper.updateById(brand);
    }

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {
        return tbBrandMapper.selectById(id);
    }
    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        tbBrandMapper.deleteBatchIds(Arrays.asList(ids));
    }
    /**
     * 根据条件分页查询
     * @param brand
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo findPage(TbBrand brand, int pageNum, int pageSize) {
        QueryWrapper<TbBrand> queryWrapper = new QueryWrapper<>();
        if (brand!=null){
            if(brand.getName()!=null && brand.getName().length()>0){
                queryWrapper.like("name","%"+brand.getName()+"%");
            }
            if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
                queryWrapper.eq("first_char",brand.getFirstChar());
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> tbBrands = tbBrandMapper.selectList(queryWrapper);
        PageInfo pageInfo = new PageInfo(tbBrands);
        return pageInfo;
    }
    /**
     * 查询品牌下拉列表数据
     * @return
     */
    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }


}
