# jeesite自己修改版

## 1. jeesite框架下学习尝试集成swagger(1)

### 1. 第一种方式：
https://blog.csdn.net/opopopwqwqwq/article/details/75087748
同上
https://blog.csdn.net/weixin_40009737/article/details/80688706

http://www.cnblogs.com/tosser/tag/JeeSite%E4%BA%8C%E6%AC%A1%E5%BC%80%E5%8F%91/

jeesite框架，免登陆直接访问页面设置。
https://blog.csdn.net/qq_36841482/article/details/76192280

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


## 2. JeeSite 工作流的应用实例
https://www.w3cschool.cn/jeesite/oisc1s10.html


