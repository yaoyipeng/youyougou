package com.yyg.heaven.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.service.ITbGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/goods")
public class TbGoodsController {

    @Reference
    private ITbGoodsService tbGoodsService;
    /*@Reference
    private ItemSearchService itemSearchService;*/
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueSolrDeleteDestination;//用户在索引库中删除记录

    @Autowired
    private Destination topicPageDeleteDestination;//用于删除静态网页的消息

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    public Result delete(Long [] ids){
        try {
            TbGoods tbGoods = new TbGoods();
            tbGoods.setIsDelete("1");
            for (Long id : ids) {
                tbGoods.setId(id);
                tbGoodsService.updateById(tbGoods);
            }
            // 删除索引
            jmsTemplate.send(queueSolrDeleteDestination, session -> session.createObjectMessage(ids));
            //删除页面
            jmsTemplate.send(topicPageDeleteDestination, session -> session.createObjectMessage(ids));
//            itemSearchService.deleteByGoodsIds(Arrays.asList(ids));
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 修改商品
     * @param tbGoods
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody TbGoods tbGoods){
        try {
            TbGoods one = tbGoodsService.findOne(tbGoods.getId());
            //判断当前用户操作的是否是自己的商品
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            if(!sellerId.equals(one.getSellerId()) || !sellerId.equals(tbGoods.getSellerId())){
                return  new Result(false,"非法操作！");
            }
            tbGoodsService.updateTbGoods(tbGoods);
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
    public TbGoods findOne(@PathVariable(value = "id") Long id){
        return tbGoodsService.findOne(id);
    }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findPage")
    public Page<TbGoods> findPage(
            @RequestParam(name = "page",required = true,defaultValue = "1") int page,
            @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        Page<TbGoods> tbGoodsPage = new Page<>(page, rows);
        return tbGoodsService.page(tbGoodsPage);
    }

    /**
     * 查询全部商品
     * @return
     */
    @GetMapping("findAll")
    public List<TbGoods> findAll(){
        return tbGoodsService.list();
    }

    /**
     * 新增商品方法
     * @param goods
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody TbGoods goods){
        // 获取登录名
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        // 设置商家ID
        goods.setSellerId(sellerId);
        try {
            tbGoodsService.add(goods);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }

    }

    /**
     * 根据条件分页查询
     * @param goods
     * @param page
     * @param rows
     * @return
     */
    @PostMapping("search")
    public Page<TbGoods> search(
                                @RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                @RequestParam(name = "rows",required = true,defaultValue = "10") int rows,
                                @RequestBody TbGoods goods){
        //获取商家ID
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        //添加查询条件
        goods.setSellerId(sellerId);
        return tbGoodsService.findPageLike(goods,page,rows);
    }

}
