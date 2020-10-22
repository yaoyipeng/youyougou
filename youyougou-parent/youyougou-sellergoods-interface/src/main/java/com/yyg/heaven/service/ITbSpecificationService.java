package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.pojo.TbSpecification;

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
public interface ITbSpecificationService extends IService<TbSpecification> {
    /**
     * 查询全部页面
     * @return
     */
    List<TbSpecification> findAll();

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo findPage(int pageNum, int pageSize);
    /**
     * 增加
     */
    void add(TbSpecification specification);

    /**
     * 根据ID查询一个
     * @param id
     * @return
     */
    TbSpecification findOne(Long id);

    /**
     * 修改
     * @param specification
     */
    void update(TbSpecification specification);

    /**
     * 批量删除（级联）
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 根据条件模糊查询
     * @param specification
     * @param page
     * @param rows
     * @return
     */
    PageInfo findPageLike(TbSpecification specification, int page, int rows);
    /**
     * 规格下拉列表
     * @return
     */
    List<Map> optionlist();
}
