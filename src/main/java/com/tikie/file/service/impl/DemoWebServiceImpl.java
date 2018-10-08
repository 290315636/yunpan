/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年10月8日
 * 修改历史：
 * 		1、[2018年10月8日]创建文件 by zhaocs
 */
package com.tikie.file.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tikie.file.service.DemoWebService;

/**
 * @author zhaocs
 *
 */
@WebService  //@WebService表示该类是一个服务类，需要发布其中的public的方法
public class DemoWebServiceImpl implements DemoWebService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /* (non-Javadoc)
     * @see com.tikie.file.service.DemoWebService#queryWeather(java.lang.String)
     */
    @Override
    public String queryWeather(String cityName) {
        logger.debug("获取城市名:{}", cityName);
        return cityName + "暴雨";
    }

}
