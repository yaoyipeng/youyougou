package com.yyg.heaven.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {

	@Reference
	private ItemSearchService itemSearchService;
	
	@PostMapping("/search")
	public Map<String, Object> search(@RequestBody Map searchMap ){
		return  itemSearchService.search(searchMap);
	}	
}