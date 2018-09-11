# jeesite自己修改版

## jeesite框架的介绍

[jeesite 框架的简单应用](http://www.cnblogs.com/zhukaixin/p/9156069.html)
[jeesite论坛](http://www.jeesite.net/forum.php)
[Jeesite官方文档 ](http://www.jeesite.net/forum.php?mod=viewthread&tid=67&extra=)
[w3cschool上的文档](https://www.w3cschool.cn/jeesite/95ne1s11.html)
[Jeesite 商业版本 版本制作计划](http://www.jeesite.net/forum.php?mod=viewthread&tid=205)


## 1. jeesite框架下学习尝试集成swagger(1)

### 1. 第一种方式：
https://blog.csdn.net/opopopwqwqwq/article/details/75087748
同上
https://blog.csdn.net/weixin_40009737/article/details/80688706
http://www.cnblogs.com/tosser/tag/JeeSite%E4%BA%8C%E6%AC%A1%E5%BC%80%E5%8F%91/
修改
com.thinkgem.jeesite.common.web.SwaggerConfig
@ComponentScan(basePackages ={"com.thinkgem.jeesite.modules.cms.web"})
spring-mvc.xml
<context:component-scan base-package="com.thinkgem.jeesite.modules.cms.web" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.context.annotation.Configuration"/>
</context:component-scan>
经测试，SwaggerConfig的权重要高于spring-mvc.xml
http://127.0.0.1:8080/static/swagger-ui-2.2.10/dist/index.html


### 2. 第二种方式

https://blog.csdn.net/qq_28802477/article/details/79786085
（项目中用的是第二种方式）
项目访问路径：
http://localhost:8080/swagger-ui.html#/


## 2. 注释掉百度统计代码

\jeesite\src\main\webapp\WEB-INF\views\layouts\blank.jsp
\jeesite\src\main\webapp\WEB-INF\views\layouts\default.jsp
\jeesite\src\main\webapp\WEB-INF\views\modules\cms\front\themes\basic\layouts\default.jsp
这三个文件中，有下面的代码（已注释）
    <!-- Baidu tongji analytics -->
	<%--<script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>--%>

## 3. 防止表单重复提交

\jeesite\src\main\webapp\static\common\jeesite.js
//添加当表单提交时，防止表单重复提交（统一方法）
$.validator.setDefaults({
    submitHandler: function() {
        $('form [type="submit"]').attr("disabled","disabled");
        loading('正在提交，请稍等...');
        form.submit();
    }
});

## 4. 修改代码生成器生成代码路径
在\jeesite\src\main\resources\jeesite.properties中，修改 projectPath
projectPath=E\:\\workspace\\jeesite

## 4. JeeSite 工作流的应用实例
https://www.w3cschool.cn/jeesite/oisc1s10.html

# 一些记录

## 1.修改登录（原登录sysLogin.jsp，改为sysLogin2.jsp）
修改 \jeesite\src\main\java\com\thinkgem\jeesite\modules\sys\web\LoginController.java
return "modules/sys/sysLogin"
把sysLogin修改为 sysLogin2
不过感觉这个sysLogin2没有sysLogin好看，所以修改回去


Jeesite之login模块的从头到尾整合
https://blog.csdn.net/weixin_40595244/article/details/80883129





