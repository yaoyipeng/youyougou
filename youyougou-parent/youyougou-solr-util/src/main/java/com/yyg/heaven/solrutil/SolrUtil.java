package com.yyg.heaven.solrutil;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyg.heaven.mapper.TbItemMapper;
import com.yyg.heaven.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 影耀子
 */
@Component
public class SolrUtil {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private SolrTemplate solrTemplate;
	
	/**
	 * 导入商品数据
	 */
	public void importItemData(){
		QueryWrapper<TbItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("status",1);//已审核
		List<TbItem> itemList = itemMapper.selectList(queryWrapper);
		System.out.println("===商品列表===");
		for(TbItem item:itemList){
			Map specMap= JSON.parseObject(item.getSpec());//将spec字段中的json字符串转换为map
			item.setSpecMap(specMap);//给带注解的字段赋值
			System.out.println(item.getTitle());			
		}
		solrTemplate.saveBeans("collection1",itemList);
		solrTemplate.commit("collection1");
		System.out.println("===结束===");			
	}	

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
		SolrUtil solrUtil=  (SolrUtil) context.getBean("solrUtil");
		solrUtil.importItemData();
	}
}