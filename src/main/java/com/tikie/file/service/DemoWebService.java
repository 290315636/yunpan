/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年10月8日
 * 修改历史：
 * 		1、[2018年10月8日]创建文件 by zhaocs
 */
package com.tikie.file.service;

/**
 * @author zhaocs
 * WebService使用例子,模拟获取天气预报数据
 */
public interface DemoWebService {

    public String queryWeather(String cityName);
}
