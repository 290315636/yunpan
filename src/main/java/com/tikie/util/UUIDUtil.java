/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月13日
 * 修改历史：
 * 		1、[2018年7月13日]创建文件 by zhaocs
 */
package com.tikie.util;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaocs
 *
 */
public class UUIDUtil {
    private static Logger logger = LoggerFactory.getLogger(Md5.class);
    
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        logger.debug("格式前的UUID ：{} " , UUID.randomUUID());
        logger.debug("格式化后的UUID ：{}" , getUUID());
    }
}
