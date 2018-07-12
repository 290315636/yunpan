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

    1.0.1 更新说明文档
    1.0.0 初始化系统