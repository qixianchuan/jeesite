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

# 学习记录

[jeesite快速开发平台(七)----代码生成原理](https://blog.csdn.net/u011781521/article/details/79322942)

在java文件中的注释，说明jeesite借鉴了springside
 * Copyright (c) 2005-2012 springside.org.cn
 
# 一些记录

## 1.修改登录（原登录sysLogin.jsp，改为sysLogin2.jsp）
修改 \jeesite\src\main\java\com\thinkgem\jeesite\modules\sys\web\LoginController.java
return "modules/sys/sysLogin"
把sysLogin修改为 sysLogin2
不过感觉这个sysLogin2没有sysLogin好看，所以修改回去


Jeesite之login模块的从头到尾整合
https://blog.csdn.net/weixin_40595244/article/details/80883129


# 发现的问题
## 1. cms添加文章报错
内容管理》内容发布》点击左边的树》文章添加，
点击父节点（点击子节点的时候不报错），会报这样的错
进一步测试会发现
点击友情链接的时候没问题
点击组织机构 质量检验 软件介绍 这三个父节点有问题
后台会报下面的错误：
java.lang.NullPointerException
	at com.thinkgem.jeesite.modules.cms.utils.CmsUtils.addViewConfigAttribute(CmsUtils.java:302)
	at com.thinkgem.jeesite.modules.cms.web.ArticleController.form(ArticleController.java:101)
	at com.thinkgem.jeesite.modules.cms.web.ArticleController$$FastClassBySpringCGLIB$$f2684e8b.invoke(<generated>)
	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:718)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)
	at org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor$1.proceed(AopAllianceAnnotationsAuthorizingMethodInterceptor.java:82)
	at org.apache.shiro.authz.aop.AuthorizingMethodInterceptor.invoke(AuthorizingMethodInterceptor.java:39)
	at org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor.invoke(AopAllianceAnnotationsAuthorizingMethodInterceptor.java:115)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:653)
	at com.thinkgem.jeesite.modules.cms.web.ArticleController$$EnhancerBySpringCGLIB$$1e2f88a.form(<generated>)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:222)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:110)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:775)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:705)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:959)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:893)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:965)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:856)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:841)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at com.opensymphony.sitemesh.webapp.SiteMeshFilter.obtainContent(SiteMeshFilter.java:129)
	at com.opensymphony.sitemesh.webapp.SiteMeshFilter.doFilter(SiteMeshFilter.java:77)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)
	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)
	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)
	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)
	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)
	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)
	at org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:365)
	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)
	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)
	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:383)
	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)
	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)
	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:344)
	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:261)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:85)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)
	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:650)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1459)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:748)




