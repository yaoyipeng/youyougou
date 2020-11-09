package com.yyg.heaven.page.service.impl;

import com.yyg.heaven.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * @author 影耀子
 */
@Component
public class PageDeleteListener implements MessageListener {

	@Autowired
	private ItemPageService itemPageService;
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage= (ObjectMessage)message;
		try {
			Long[] goodsIds = (Long[]) objectMessage.getObject();
			System.out.println("ItemDeleteListener监听接收到消息..."+goodsIds);
			boolean b = itemPageService.deleteItemHtml(goodsIds);
			System.out.println("网页删除结果："+b);			
		} catch (JMSException e) {
			e.printStackTrace();
		}			
	}
}