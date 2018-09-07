package com.thinkgem.jeesite.common.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;


/**
 * @author xiegx
 * @version 创建时间：2016-8-16 下午2:01:10
 * SwaggerUI配置
 */
@Configuration
@EnableSwagger
@EnableWebMvc
@ComponentScan(basePackages ={"com.thinkgem.jeesite.modules.sys.web"})
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*")
                .swaggerGroup("XmPlatform")
                .apiVersion("1.0.0");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    /*
     * "标题 title",
     * "描述 description",
     * "termsOfServiceUrl",
     * "联系邮箱 contact email",
     * "许可证的类型 license type",
     * "许可证的链接 license url"
     */
    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "project API",
                "RESTful API",
                "",//
                "JAVA",
                "",
                "");
        return apiInfo;
    }
}
