/**
 * 
 */
package com.tikie.file.active;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.tikie.file.model.FileTree;
import com.tikie.file.service.FileTreeService;

/**
 * @author zhaocs
 * 基于队列的消费者
 */
@Service
public class Consumer {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	FileTreeService fileTreeService;
	
	@JmsListener(destination = "file.queue")
	public void receiveMsg(ObjectMessage text) {
		
		try {
			FileTree record = (FileTree)text.getObject();
			if(logger.isDebugEnabled()) {
				logger.debug("异步消息处理 >>[接收]>>文件上传：{}", record.toString());
			}
			
			// 更新父级文件夹的大小
			fileTreeService.updateFileTreeFolderSize(record);
			// 更新文件统计信息
			
		} catch (JMSException e) {
			logger.error("消费者处理异常:{}", e.getMessage());
		}
	}
}
