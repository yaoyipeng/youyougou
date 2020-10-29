package com.yyg.heaven.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbItemCat;
import com.yyg.heaven.service.ITbItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/itemCat")
public class TbItemCatController {

    @Reference
    private ITbItemCatService tbItemCatService;
    /**
     * 根据上级ID查询列表分类
     * @param parentId
     * @return
     */
    @GetMapping("findByParentId")
    public List<TbItemCat> findByParentId(Long parentId){
        return tbItemCatService.findByParentId(parentId);
    }

    /**
     * 添加分类
     * @param tbItemCat
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody TbItemCat tbItemCat){
        try {
            tbItemCatService.save(tbItemCat);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @GetMapping("findOne/{id}")
    public TbItemCat findOne(@PathVariable(value = "id") Long id){
        return tbItemCatService.getById(id);
    }

    /**
     * 修改分类
     * @param tbItemCat
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody TbItemCat tbItemCat){
        try {
            tbItemCatService.updateById(tbItemCat);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 批量删除分类(级联删除)
     * @param ids
     * @return
     */
    @PostMapping("/delete.do")
    public Result delete(Long [] ids){
        try {
            tbItemCatService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
