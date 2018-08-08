/**
 * 
 */
package com.tikie.file.activemq;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tikie.common.CommonEnums;
import com.tikie.common.CommonEnums.MQDestination;

/**
 * @author zhaocs
 * 基于队列的生产者
 */
@Service
public class Producer {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	// 首先需要服务器开启定时器支持activemq.xml > broker : schedulerSupport="true"
	public void send(MQDestination destination, final Serializable message){

		Destination target = null;
		if(destination.getCode() < CommonEnums.MQ_QUEUE_MIN_CODE) {
			target = new ActiveMQQueue(destination.getMessage());
		}else {
			target = new ActiveMQTopic(destination.getMessage());
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("异步消息处理 >>[发送]>>文件上传：{}", message.toString());
		}
		
		jmsMessagingTemplate.convertAndSend(target, message);
		
	}
}
