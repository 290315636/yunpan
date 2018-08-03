# yunpan
基于spring boot的文件管理系统

## yunpan 在线文件管理系统：云盘
    
    为了方便文件的在线管理，各大公司都推出了云盘系统，如：百度云盘、360云盘、腾讯的微云盘等，本系统主要目的在于学习相关技术点

### 参考网站
 
 + 如有任何问题欢迎联系作者：tikie
 
        qq:290315636
    
### 项目环境
 - Eclipse：Oxygen.3a Release (4.7.3a)
 
        彩色日志插件：http://www.mihai-nita.net/eclipse
 - jdk:1.8+
 - spring boot: 2.0.3.RELEASE

### 初始化操作
 
 + 运行服务提供者：TikieBootstrapFileuploadApplication.java
    
        Run As
        Run Java Application或Spring Boot App
 + 或命令行启动方式：
 
        java -jar tikie-yunpan-0.0.1-SNAPSHOT.jar.jar

 + *本项目的默认只提供dev分支的更新权限*
 
 + 设置默认push/pull行为(push当前分支到远程同名分支，如果远程同名分支不存在则自动创建同名分支)
    
       git config push.default "current"
       git config pull.default "current"
       
       #在对应的分支上执行：如dev分支
       git branch --set-upstream-to=origin/dev
       
 + 生成代码及使用步骤
 
       1. 更改resources/generator/generatorConfig.properties中的配置（主要更改模块名）
       2. 更改resources/generator/generatorConfig.xml关于表的配置（基于哪些表生成对应的代码，已经有的模块注释掉）
       3. 运行maven命令：mvn mybatis-generator:generate
       4. 书写service层接口及实现
       5. 书写测试类
       ps:可参考test模块学习基本使用方法
 
 
 + 实现分页步骤
 
       1. 书写对应service接口，接口提供参数（int pageNo, int pageSize）
       2. 实现对应的接口，在返回mapper的方法前执行：PageHelper.startPage(pageNo, pageSize);
       3. controller中调用对应接口，传入pageNo和pageSize，返回对应数据
       4. 书写测试类
       ps:可参考test模块学习基本使用方法
 
 
### 实现功能点
    1. 多文件上传
    2. swagger api管理：http://localhost:8080/swagger-ui.html
    3. bootstrap + thymeleaf模板
    4. 断点上传--待完成
    5. 压缩存储--待完成
    6. 在线预览--待完成
    7. 网络收藏--待完成
    8. 分享（公开、私密）--待完成
    9. 下载（包括断点下载）--待完成
    
### 历史更新
    
    1.0.6 实现activemq推送消息
    1.0.5 精细化配置开发环境和生产环境
    1.0.4 规范化数据存储位置:物理存放地址basePath+type+年月
    1.0.3 打通数据库：mybatis + generator + druid + mysql
    1.0.2 swagger api集成完毕
    1.0.1 多文件上传接口及页面实现
    1.0.0 初始化系统