package com.yyg.heaven.page.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyg.heaven.mapper.TbGoodsDescMapper;
import com.yyg.heaven.mapper.TbGoodsMapper;
import com.yyg.heaven.mapper.TbItemCatMapper;
import com.yyg.heaven.mapper.TbItemMapper;
import com.yyg.heaven.page.service.ItemPageService;
import com.yyg.heaven.pojo.TbGoods;
import com.yyg.heaven.pojo.TbGoodsDesc;
import com.yyg.heaven.pojo.TbItem;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemPageServiceImpl implements ItemPageService {

	@Value("${pagedir}")
	private String pagedir;
	
	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	
	@Autowired
	private TbGoodsMapper goodsMapper;
	
	@Autowired
	private TbGoodsDescMapper goodsDescMapper;

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private TbItemMapper itemMapper;
		
	@Override
	public boolean genItemHtml(Long goodsId){				
		try {
			Configuration configuration = freeMarkerConfig.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			Map dataModel=new HashMap<>();
			//1.加载商品表数据
			TbGoods goods = goodsMapper.selectById(goodsId);
			dataModel.put("goods", goods);			
			//2.加载商品扩展表数据			
			TbGoodsDesc goodsDesc = goodsDescMapper.selectById(goodsId);
			dataModel.put("goodsDesc", goodsDesc);
			//3.商品分类
			String itemCat1 = itemCatMapper.selectById(goods.getCategory1Id()).getName();
			String itemCat2 = itemCatMapper.selectById(goods.getCategory2Id()).getName();
			String itemCat3 = itemCatMapper.selectById(goods.getCategory3Id()).getName();
			dataModel.put("itemCat1", itemCat1);
			dataModel.put("itemCat2", itemCat2);
			dataModel.put("itemCat3", itemCat3);
			//4.SKU列表
			TbItem tbItem = new TbItem();
			tbItem.setStatus("1");//状态为有效
			tbItem.setGoodsId(goodsId);//指定SPU ID
			QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>(tbItem);
			tbItemQueryWrapper.orderByDesc("is_default");//按照状态降序，保证第一个为默认
			List<TbItem> itemList = itemMapper.selectList(tbItemQueryWrapper);
			dataModel.put("itemList", itemList);

			Writer out = new OutputStreamWriter(new FileOutputStream(pagedir+goodsId+".html"),"UTF-8");
			template.process(dataModel, out);
			out.close();
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}