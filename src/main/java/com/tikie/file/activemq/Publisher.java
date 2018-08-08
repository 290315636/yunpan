/**
 * 
 */
package com.tikie.file.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhaocs
 * 基于订阅的发布者
 */
@Service
public class Publisher {

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void publish(String destinationName, String message) {
		System.out.println("发送topic消息:" + message);
		Destination destinatin = new ActiveMQTopic(destinationName);
		jmsMessagingTemplate.convertAndSend(destinatin, message);
	}
}
