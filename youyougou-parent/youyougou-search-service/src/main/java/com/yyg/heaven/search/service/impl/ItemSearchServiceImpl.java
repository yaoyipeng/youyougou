package com.yyg.heaven.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yyg.heaven.pojo.TbItem;
import com.yyg.heaven.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Dubbox-code-youyougou
 * @description
 * @author: 影耀子（YingYew）
 * @create: 2020-11-03 14:45
 **/
@Service(timeout=3000)
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Map<String, Object> search(Map searchMap) {
        Map<String,Object> map=new HashMap<>();
        Query query=new SimpleQuery("*:*");
        //添加查询条件
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);
        ScoredPage<TbItem> page = solrTemplate.queryForPage("collection1",query, TbItem.class);
        map.put("rows", page.getContent());
        return map;
    }
}
