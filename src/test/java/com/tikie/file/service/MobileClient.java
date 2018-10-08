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
 * 测试WebService客户端，调用查询手机归属地
 * 该种方式使用简单，但一些关键的元素在代码生成时写死在生成代码中，不方便维护，所以仅用于测试
 */
public class MobileClient {
    public static void main(String[] args) {
        //创建服务视图
        MobileCodeWS mobileCodeWS=new MobileCodeWS();
        //获取服务实现类
        MobileCodeWSSoap mobileCodeWSSoap= mobileCodeWS.getPort(MobileCodeWSSoap.class);
        //调用查询方法
        String message=mobileCodeWSSoap.getMobileCodeInfo("13621239684", null);
        System.out.println(message);

    }
}
