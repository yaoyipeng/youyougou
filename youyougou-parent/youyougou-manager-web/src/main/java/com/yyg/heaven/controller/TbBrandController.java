package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbBrand;
import com.yyg.heaven.service.ITbBrandService;
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
 * 品牌controller
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/brand")
public class TbBrandController {

    //理解成远程注入
    @Reference
    private ITbBrandService tbBrandService;

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<TbBrand> findAll(){
        return tbBrandService.findAll();
    }

    /**
     * 分页查询数据
     * @return
     */
    @RequestMapping("/findPage.do")
    public PageInfo findPage(@RequestParam(name = "page",required = true,defaultValue = "1")int page,
                                  @RequestParam(name = "rows",required = true,defaultValue = "10")int size){
        PageInfo pageInfo = tbBrandService.findPage(page, size);
        return pageInfo;
    }

    /**
     * 添加
     * @param brand
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody TbBrand brand){
        try {
            tbBrandService.add(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param brand
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try {
            tbBrandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }
    /**
     * 获取实体
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return tbBrandService.findOne(id);
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            tbBrandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    /**
     * 查询+分页
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageInfo search(@RequestBody TbBrand brand,
                           @RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                   @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        System.out.println("sear");
        return tbBrandService.findPage(brand, page, rows);
    }

    /**
     * 查询品牌下拉列表数据
     * @return
     */
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return tbBrandService.selectOptionList();
    }
}
