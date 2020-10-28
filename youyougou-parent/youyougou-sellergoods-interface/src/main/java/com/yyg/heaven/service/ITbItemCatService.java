package com.yyg.heaven.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbItemCat;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbItemCatService extends IService<TbItemCat> {
    /**
     * 根据上级ID查询列表分类
     * @param parentId
     * @return
     */
    List<TbItemCat> findByParentId(Long parentId);

    /**
     * 批量删除分类(级联删除)
     * @param ids
     */
    void delete(Long[] ids);
}
