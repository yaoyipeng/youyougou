package com.yyg.heaven.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbTypeTemplate;
import com.yyg.heaven.service.ITbTypeTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/typeTemplate")
public class TbTypeTemplateController {

    @Reference
    private ITbTypeTemplateService tbTypeTemplateService;


    /**
     * 查询全部
     * @return
     */
    @GetMapping("/findAll.do")
    public List<TbTypeTemplate> findAll(){
        return tbTypeTemplateService.list();
    }

    /**
     * 分页查询
     * @return
     */
    @GetMapping("/findPage.do")
    public PageInfo findPage(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                             @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        return tbTypeTemplateService.findPage(page,rows);
    }

    /**
     * 添加类型模板
     * @param typeTemplate
     * @return
     */
    @PostMapping("/add.do")
    public Result add(@RequestBody TbTypeTemplate typeTemplate){
        try {
            tbTypeTemplateService.save(typeTemplate);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    /**
     * 添加类型模板
     * @param typeTemplate
     * @return
     */
    @PostMapping("/update.do")
    public Result update(@RequestBody TbTypeTemplate typeTemplate){
        try {
            tbTypeTemplateService.updateById(typeTemplate);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    @GetMapping("/findOne/{id}")
    public TbTypeTemplate findOne(@PathVariable(value = "id") Long id){
        return tbTypeTemplateService.getById(id);
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete.do")
    public Result delete(@RequestBody List<Long> ids){
        try {
            tbTypeTemplateService.removeByIds(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 根据条件模糊查询
     * @param typeTemplate
     * @param page
     * @param rows
     * @return
     */
    @PostMapping("/search.do")
    public PageInfo search(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                           @RequestParam(name = "rows",required = true,defaultValue = "10") int rows,
                           @RequestBody TbTypeTemplate typeTemplate){
        return tbTypeTemplateService.findPageLike(typeTemplate, page, rows);
    }
    /**
     * 查询类型下拉列表数据
     * @return
     */
    @GetMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return tbTypeTemplateService.selectOptionList();
    }
    /**
     * 查询规格列表
     * @param id
     * @return
     */
    @GetMapping("findSpecList/{id}")
    public List<Map> findSpecList(@PathVariable(value = "id") Long id){
        return tbTypeTemplateService.findSpecList(id);
    }
}
