/**
 * 
 */
package com.tikie.file.activemq;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhaocs
 * 基于订阅的发布者
 */
@Service
public class Publisher {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void publish(String destinationName, String message) {
	    if(logger.isDebugEnabled()) {
	        logger.debug("发送topic消息:" + message);
	    }
		Destination destinatin = new ActiveMQTopic(destinationName);
		jmsMessagingTemplate.convertAndSend(destinatin, message);
	}
}
