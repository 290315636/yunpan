/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年10月8日
 * 修改历史：
 * 		1、[2018年10月8日]创建文件 by zhaocs
 */
package com.tikie.file.service;

import java.io.IOException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * @author zhaocs
 * 标准的开发方式
 * 获得国内手机号码归属地省份、地区和手机卡类型信息
输入参数：mobileCode = 字符串（手机号码，最少前7位数字），userID = 字符串（商业用户ID） 免费用户为空字符串；返回数据：字符串（手机号码：省份 城市 手机卡类型）。
 * 13621239684：北京 北京 北京移动神州行卡
 * 没有此号码记录
 */
public class MobileClient2 {
    public static void main(String[] args) throws IOException {
        //创建WSDL文件的URL
        URL wsdlDocumentLocation=new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl"); 
        //创建服务名称
        //1.namespaceURI - 命名空间地址
        //2.localPart - 服务视图名
        QName serviceName=new QName("http://WebXml.com.cn/","MobileCodeWS");
        Service service=Service.create(wsdlDocumentLocation, serviceName);

        //获取服务实现类
        MobileCodeWSSoap mobileCodeWSSoap= service.getPort(MobileCodeWSSoap.class);
        //调用方法
        String message=mobileCodeWSSoap.getMobileCodeInfo("13621239684", null);
        System.out.println(message);

    }
}
