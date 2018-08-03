/**
 * 
 */
package com.tikie.config;

import java.io.File;

import javax.jms.ConnectionFactory;
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

/**
 * @author zhaocs
 * 改变临时文件的存储路径
 */
@Configuration
public class MultipartConfig {
	/**
     * 文件上传临时路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + "/data/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
    
    // 在pub/sub模式中，对消息的监听需要对containerFactory进行以下配置
    @Bean
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory){
    	SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
    	factory.setConnectionFactory(connectionFactory);
    	factory.setPubSubDomain(true);
    	return factory;
    }
}
