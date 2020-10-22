package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbSpecification;
import com.yyg.heaven.service.ITbSpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/specification")
public class TbSpecificationController {

    @Reference
    private ITbSpecificationService tbSpecificationService;

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return tbSpecificationService.findAll();
    }


    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findPage")
    public PageInfo findPage(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                             @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        return tbSpecificationService.findPage(page, rows);
    }

    /**
     * 添加
     * @param specification
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbSpecification specification){
        try {
            tbSpecificationService.add(specification);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }


    /**
     * 根据主键id查询一个
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbSpecification findOne(Long id){
        return tbSpecificationService.findOne(id);
    }

    /**
     * 修改
     * @param specification
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbSpecification specification){
        try {
            tbSpecificationService.update(specification);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            tbSpecificationService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 根据条件模糊查询
     * @param specification
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageInfo search(@RequestBody TbSpecification specification,
                           @RequestParam(name = "page",required = true,defaultValue = "1") int page,
                           @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        return tbSpecificationService.findPageLike(specification, page, rows);
    }

    /**
     * 规格下拉列表
     * @return
     */
    @RequestMapping("/optionlist")
    public List<Map> optionlist(){
        return tbSpecificationService.optionlist();
    }

}
