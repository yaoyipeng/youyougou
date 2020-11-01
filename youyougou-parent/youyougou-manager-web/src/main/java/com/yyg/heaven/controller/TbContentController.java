package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.heaven.content.service.ITbContentService;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
            tbContentService.save(tbContent);
            //清除缓存
            redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
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
            //查询修改前的分类Id
            Long categoryId = tbContentService.getById(tbContent.getId()).getCategoryId();
            redisTemplate.boundHashOps("content").delete(categoryId);
            tbContentService.updateById(tbContent);
            //如果分类ID发生了修改,清除修改后的分类ID的缓存
            if(categoryId.longValue()!=tbContent.getCategoryId().longValue()){
                redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
            }
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
            for (Long id : ids) {
                //清除缓存
                Long categoryId = tbContentService.getById(id).getCategoryId();//广告分类ID
                redisTemplate.boundHashOps("content").delete(categoryId);
            }
            tbContentService.removeByIds(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
