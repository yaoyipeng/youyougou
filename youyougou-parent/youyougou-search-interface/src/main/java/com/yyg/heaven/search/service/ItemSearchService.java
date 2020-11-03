package com.yyg.heaven.search.service;

import java.util.Map;

public interface ItemSearchService {
	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	Map<String,Object> search(Map searchMap);
}