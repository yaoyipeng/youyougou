package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.pojo.TbBrand;

import java.util.List;
import java.util.Map;

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

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo findPage(int page, int size);
    /**
     * 增加
     */
    void add(TbBrand brand);
    /**
     * 修改
     */
    void update(TbBrand brand);
    /**
     * 根据ID获取查询一个
     * @param id
     * @return
     */
    TbBrand findOne(Long id);
    /**
     * 批量删除
     * @param ids
     */
    void delete(Long [] ids);

    /**
     * 根据条件分页查询
     * @param brand
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo findPage(TbBrand brand, int pageNum,int pageSize);
    /**
     * 查询品牌下拉列表数据
     * @return
     */
    List<Map> selectOptionList();
}
