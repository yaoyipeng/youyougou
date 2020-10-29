package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.*;
import com.yyg.heaven.pojo.*;
import com.yyg.heaven.service.ITbGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

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
        if("1".equals(goods.getIsEnableSpec())){
            for (TbItem item : goods.getItemList()) {
                // 标题
                String title = goods.getGoodsName();
                Map<String,Object> specMap = JSON.parseObject(item.getSpec());
                for (String key : specMap.keySet()) {
                    title +=" "+ specMap.get(key);
                }
                item.setTitle(title);
                setItemValus(goods,item);
                tbItemMapper.insert(item);

            }
        }else {
            TbItem item=new TbItem();
            item.setTitle(goods.getGoodsName());//商品KPU+规格描述串作为SKU名称
            item.setPrice( goods.getPrice() );//价格
            item.setStatus("1");//状态
            item.setIsDefault("1");//是否默认
            item.setNum(99999);//库存数量
            item.setSpec("{}");
            setItemValus(goods,item);
            tbItemMapper.insert(item);
        }
    }

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
