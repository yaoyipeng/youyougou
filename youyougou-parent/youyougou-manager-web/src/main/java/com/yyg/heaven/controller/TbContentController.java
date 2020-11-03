package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.heaven.content.service.ITbContentService;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/content")
public class TbContentController {

    @Reference
    private ITbContentService tbContentService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询并分页
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findPage")
    public Page<TbContent> findPage(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                  @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        Page<TbContent> tbContentPage = new Page<>(page,rows);
        return tbContentService.page(tbContentPage);
    }

    /**
     * 添加
     * @param tbContent
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody TbContent tbContent){
        try {
            tbContentService.add(tbContent);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param tbContent
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody TbContent tbContent){
        try {
            tbContentService.updateTbContentById(tbContent);
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
    @GetMapping("findOne/{id}")
    public TbContent findOne(@PathVariable(value = "id") Long id){
        return tbContentService.getById(id);
    }

    /**
     * 批量删除
     * @return
     */
    @PostMapping("delete")
    public Result delete(Long [] ids){
        try {
            tbContentService.deleteByIds(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
