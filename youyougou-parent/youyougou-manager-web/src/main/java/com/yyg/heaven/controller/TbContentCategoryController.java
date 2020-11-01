package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.heaven.content.service.ITbContentCategoryService;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbContentCategory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 内容分类 前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/contentCategory")
public class TbContentCategoryController {

    @Reference
    private ITbContentCategoryService tbContentCategoryService;

    /**
     * 查询全部
     * @return
     */
    @GetMapping("findAll")
    public List<TbContentCategory> findAll(){
        return tbContentCategoryService.list();
    }

    /**
     * 根据条件分页查询
     * @param page
     * @param rows
     * @param tbContentCategory
     * @return
     */
    @PostMapping("search")
    public Page<TbContentCategory> search(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                          @RequestParam(name = "rows",required = true,defaultValue = "10") int rows,
                                          @RequestBody TbContentCategory tbContentCategory){
        return tbContentCategoryService.findPageLike(page,rows,tbContentCategory);
    }

    /**
     * 添加
     * @param tbContentCategory
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody TbContentCategory tbContentCategory){
        try {
            tbContentCategoryService.save(tbContentCategory);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param tbContentCategory
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody TbContentCategory tbContentCategory){
        try {
            tbContentCategoryService.updateById(tbContentCategory);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    @GetMapping("findOne/{id}")
    public TbContentCategory findOne(@PathVariable(value = "id") Long id){
        return tbContentCategoryService.getById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    public Result delete(Long [] ids){
        try {
            tbContentCategoryService.removeByIds(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
