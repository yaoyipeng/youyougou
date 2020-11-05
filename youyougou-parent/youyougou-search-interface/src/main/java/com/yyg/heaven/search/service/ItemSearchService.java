package com.yyg.heaven.search.service;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {
	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	Map<String,Object> search(Map searchMap);
	/**
	 * 导入数据
	 * @param list
	 */
	void importList(List list);
	/**
	 * 删除数据
	 * @param ids
	 */
	void deleteByGoodsIds(List goodsIdList);
}