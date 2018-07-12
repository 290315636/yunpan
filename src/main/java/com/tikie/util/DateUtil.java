/**
 * 
 * 项目名称：tikie-bootstrap-fileupload
 * 创建日期：2018年7月12日
 * 修改历史：
 * 		1、[2018年7月12日]创建文件 by zhaocs
 */
package com.tikie.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author zhaocs
 *
 */
public class DateUtil extends DateUtils{

    /**
     * 
     * @param pattern 如：yyyyMMddHHmmss/yyyy-MM-dd HH:mm:ss
     * @return 
     */
    public static String getCurrentTime(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
        return df.format(new Date());
    }

}
