/**
 * 
 */
package com.tikie.file.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author zhaocs
 * 订阅者
 */
@Service
public class Subscriber {
	
	@JmsListener(destination = "test.topic", containerFactory = "myJmsContainerFactory")
	public void subscribe(String text) {
		System.out.println("收到订阅的消息:" + text);
	}
}
