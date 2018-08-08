# yunpan
基于spring boot的文件管理系统。本系统不和具体用户绑定，旨在开发为一个功能组件，可以轻易改造为云服务组件。

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


### 技术点
#### 前端
	1. thymeleaf模板
	2. bootstrap样式
	3. bootstrap组件：messenger--消息提示
	4. bootstrap组件：contextmenu--右键监听
	5. jquery.mousewheel组件--鼠标滚动监听 
	6. [阿里]juicer组件--html片段组装
	7. animate组件--动态效果
	8. file-input组件--文件上传[固定的东西有点多，可以考虑自己现实]

#### 前端
	1. SpringBoot
	2. swagger--API接口管理
	3. activemq--异步消息处理(文件更新后，更新父级文件夹大小)
	4. druid--数据库连接池（优化数据库链接等操作）
	5. mybatis--简化DAO操作
	6. generator--生成DAO基本操作代码
	7. pagehelper--实现接口分页
	8. quartz--实现定时任务（定时生成主题消息（如：分享数或下载数前三的文件），订阅的用户可以收到推送消息--业务逻辑待实现）


#### 宗旨
	1. 坚持模块化原则：一个功能一个模块[整个云盘为一个功能，为了其他项目的使用]
	2. 坚持工具化原则：能写成公共方法的不要重复造车
	3. 坚持详细注释原则：写清楚实现思路，预留的注释为// TODO
	4. 持续优化、集成原则：先实现一种方法，有其他优化方法了再持续更新
	5. 实现测试用例原则：不要求每个接口都写测试用例，但每个表的接口至少写两个测试用例，测过的加@Ignore标识，也可以用swagger测试
	6. 公共静态变量枚举化原则--避免冲突
	7. 公共模块、方法独立化原则--方便重复使用
	8. 独立js文件闭包化原则--避免全局变量泛滥
	9. 减少页面请求原则--尽量避免刷新页面造成的全部ajax请求，减少不必要的接口请求

### 实现功能点
    1. 多文件上传，妙传
    2. swagger api管理：http://localhost:8080/swagger-ui.html
    3. bootstrap + thymeleaf模板
    4. 断点上传--待完成
    5. 压缩存储--待完成
    6. 在线预览--待完成
    7. 网络收藏--待完成
    8. 分享（公开、私密）--待完成
    9. 下载（包括断点下载）
  
### 数据库表说明
    1. tb_file存储唯一物理文件对象，有唯一的md5值（页面生成，为实现妙传提供了方向）
    2. tb_file_tree文件列表展示（增加个用户关联表即可实现面向用户的业务逻辑）
    3. bootstrap + thymeleaf模板
    4. 断点上传--待完成
    5. 压缩存储--待完成
    6. 在线预览--待完成
    7. 网络收藏--待完成
    8. 分享（公开、私密）--待完成
    9. 下载（包括断点下载）
      
### 历史更新
    
    1.0.8 实现文件列表展示（多选、图标、单击、双击、右键效果），实现上传（可优化）、下载（可优化）、删除、重命名
    1.0.7 实现云盘页面左侧菜单栏的折叠功能（数据写死，可以另加菜单表实现动态加载）
    1.0.6 实现activemq推送消息
    1.0.5 精细化配置开发环境和生产环境
    1.0.4 规范化数据存储位置:物理存放地址basePath+年月+文件名称
    1.0.3 打通数据库：mybatis + generator + druid + mysql
    1.0.2 swagger api集成完毕
    1.0.1 多文件上传接口及页面实现
    1.0.0 初始化系统