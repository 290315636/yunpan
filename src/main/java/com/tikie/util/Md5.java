/**
 * 
 * 项目名称：tikie-bootstrap-fileupload
 * 创建日期：2018年7月12日
 * 修改历史：
 * 		1、[2018年7月12日]创建文件 by zhaocs
 */
package com.tikie.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaocs
 *
 */
public class Md5 {
    private static Logger logger = LoggerFactory.getLogger(Md5.class);

    /**
     * 获取MD5加密
     * @param pwd 需要加密的字符串
     * @return String 字符串 加密后的字符串
     */
    public static String getMd5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.info("==== MD5@code:{} ====", Md5.getMd5(Md5.getMd5("123456") + "tikie-2018"));
    }
}
