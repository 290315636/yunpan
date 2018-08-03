/**
 * 
 */
package com.tikie.file.active;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author zhaocs
 * 基于队列的消费者
 */
@Service
public class Consumer {

	@JmsListener(destination = "file.queue")
	public void receiveMsg(ObjectMessage text) {
		
		try {
			System.out.println("消息："+text.getObject().toString());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
