package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.pojo.TbItem;
import com.yyg.heaven.service.ITbGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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
    private Destination queueSolrDestination;//用于发送solr导入的消息

    @Autowired
    private JmsTemplate jmsTemplate;

    /*@Autowired
    private Destination queueSolrDeleteDestination;//用户在索引库中删除记录*/

    @Autowired
    private Destination topicPageDestination;

    /*@Reference(timeout=40000)
    private ItemPageService itemPageService;*/

    /**
     * 生成静态页（测试）
     * @param goodsId
     */
    /*@RequestMapping("/genHtml")
    public void genHtml(Long goodsId){
        itemPageService.genItemHtml(goodsId);
    }*/

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
            /*jmsTemplate.send(queueSolrDeleteDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createObjectMessage(ids);
                }
            });*/
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
        return tbGoodsService.findPageLike(goods,page,rows);
    }

    /**
     * 批量修改状态
     * @param ids
     * @param status
     * @return
     */
    @GetMapping("updateStatus")
    public Result updateStatus(Long[] ids, String status){
        try {
            TbGoods tbGoods = new TbGoods();
            tbGoods.setAuditStatus(status);
            for (Long id : ids) {
                tbGoods.setId(id);
                tbGoodsService.updateById(tbGoods);
            }
            //按照SPU ID查询 SKU列表(状态为1)
            if(status.equals("1")){//审核通过
                List<TbItem> itemList = tbGoodsService.findItemListByGoodsIdandStatus(ids, status);
                //调用搜索接口实现数据批量导入
                if(itemList.size()>0){
//                    itemSearchService.importList(itemList);
                    final String jsonString = JSON.toJSONString(itemList);
                    jmsTemplate.send(queueSolrDestination, session -> session.createTextMessage(jsonString));
                }else{
                    System.out.println("没有明细数据");
                }
                //静态页生成
                for(final Long goodsId:ids){
                    jmsTemplate.send(topicPageDestination, session -> session.createTextMessage(goodsId+""));
                }
                //静态页生成
                /*for(Long goodsId:ids){
                    itemPageService.genItemHtml(goodsId);
                }*/
            }
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }
}
