package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.*;
import com.yyg.heaven.pojo.*;
import com.yyg.heaven.service.ITbGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbGoodsServiceImpl extends ServiceImpl<TbGoodsMapper, TbGoods> implements ITbGoodsService {

    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsDescMapper tbGoodsDescMapper;

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbSellerMapper tbSellerMapper;
    /**
     * 增加
     */
    @Override
    public void add(TbGoods goods) {
        // 设置未申请状态
        goods.setAuditStatus("0");
        // 执行添加
        tbGoodsMapper.insert(goods);
        // 设置id（TbGoods中主键赋值到GoodsDesc中主键）
        TbGoodsDesc goodsDesc = goods.getGoodsDesc();
        goodsDesc.setGoodsId(goods.getId());
        // 插入商品扩展数据
        tbGoodsDescMapper.insert(goodsDesc);
        // 调用添加商品下的items方法
        addItems(goods);
    }
    /**
     * 修改商品
     * @param tbGoods
     * @return
     */
    @Override
    public void updateTbGoods(TbGoods tbGoods) {
        //修改Goods   状态由审核通过变成审核待审核
        tbGoods.setAuditStatus("0");
        tbGoodsMapper.updateById(tbGoods);
        //修改GoodsDesc
        tbGoodsDescMapper.updateById(tbGoods.getGoodsDesc());
        // 先删除items
        try {
            QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>();
            tbItemQueryWrapper.eq("goods_id",tbGoods.getId());
            tbItemMapper.delete(tbItemQueryWrapper);
        } catch (Exception e) {
            System.out.println("表示商品下无items，根据外键删除出错");
            e.printStackTrace();
        }
        // 调用添加商品下的items方法
        addItems(tbGoods);
    }
    /**
     * 根据商品ID和状态查询Item表信息
     * @param goodsIds
     * @param status
     * @return
     */
    @Override
    public List<TbItem> findItemListByGoodsIdandStatus(Long[] goodsIds, String status) {
        QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>();
        tbItemQueryWrapper.eq("status",status);
        tbItemQueryWrapper.in("goods_id", goodsIds);
        return tbItemMapper.selectList(tbItemQueryWrapper);
    }

    /**
     * 根据条件分页查询
     * @param goods
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Page<TbGoods> findPageLike(TbGoods goods, int page, int rows) {
        QueryWrapper<TbGoods> tbGoodsQueryWrapper = new QueryWrapper<>();
        if (goods!=null){
            // 根据状态查询
            if (goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
                tbGoodsQueryWrapper.eq("audit_status",goods.getAuditStatus());
            }
            // 根据sellerId查询
            if (goods.getSellerId()!=null && goods.getSellerId().length()>0){
                tbGoodsQueryWrapper.eq("seller_id",goods.getSellerId());
            }
            // 根据名称查询
//            if (StringUtils.isNotBlank(goods.getGoodsName())){
//                tbGoodsQueryWrapper.like("goods_name","%"+goods.getGoodsName()+"%");
//            }
            if (goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
                tbGoodsQueryWrapper.like("goods_name","%"+goods.getGoodsName()+"%");
            }
            tbGoodsQueryWrapper.isNull("is_delete");
        }
        Page<TbGoods> tbGoodsPage = new Page<>(page,rows);
        return tbGoodsMapper.selectPage(tbGoodsPage,tbGoodsQueryWrapper);
    }
    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    @Override
    public TbGoods findOne(Long id) {
        // 根据id查询一个spu
        TbGoods tbGoods = tbGoodsMapper.selectById(id);
        // 根据id查询spu详情
        TbGoodsDesc tbGoodsDesc = tbGoodsDescMapper.selectById(id);
        tbGoods.setGoodsDesc(tbGoodsDesc);
        // 根据id查询sku商品列表
        QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>();
        tbItemQueryWrapper.eq("goods_id",id);
        List<TbItem> tbItems = tbItemMapper.selectList(tbItemQueryWrapper);
        tbGoods.setItemList(tbItems);
        return tbGoods;
    }

    /**
     * 封装添加商品下的items
     * @param goods
     */
    private void addItems(TbGoods goods) {
        // 根据商品第三类目查询类目
        if("1".equals(goods.getIsEnableSpec())){
            for (TbItem item : goods.getItemList()) {
                // 标题
                String title = "";
                title = goods.getGoodsName();
                Map<String,Object> specMap = JSON.parseObject(item.getSpec());
                for (String key : specMap.keySet()) {
                    title += " " + specMap.get(key);
                }
                item.setTitle(title);
                // 调用初始化方法
                setItemValus(goods,item);
                tbItemMapper.insert(item);
            }
        }else {
            TbItem item=new TbItem();
            item.setTitle(goods.getGoodsName());//商品KPU+规格描述串作为SKU名称
            // 调用初始化方法
            setItemValus(goods,item);
            item.setPrice( goods.getPrice() );//价格
            item.setStatus("1");//状态
            item.setIsDefault("1");//是否默认
            item.setNum(99999);//库存数量
            item.setSpec("{}");
            tbItemMapper.insert(item);
        }
    }

    /**
     * 封装初始化方法
     * @param goods
     * @param item
     */
    private void setItemValus(TbGoods goods, TbItem item) {
        item.setGoodsId(goods.getId());//商品SPU编号
        item.setSellerId(goods.getSellerId());//商家编号
        item.setCategoryId(goods.getCategory3Id());//商品分类编号（3级）
        item.setCreateTime(new Date());//创建日期
        //品牌名称
        TbBrand brand = tbBrandMapper.selectById(goods.getBrandId());
        item.setBrand(brand.getName());
        //分类名称
        TbItemCat itemCat = tbItemCatMapper.selectById(goods.getCategory3Id());
        item.setCategory(itemCat.getName());
        //商家名称
        TbSeller seller = tbSellerMapper.selectById(goods.getSellerId());
        item.setSeller(seller.getNickName());
        //图片地址（取spu的第一个图片）
        List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class) ;
        if(imageList.size()>0){
            item.setImage ( (String)imageList.get(0).get("url"));
        }
        item.setUpdateTime(new Date());//修改日期
    }
}
