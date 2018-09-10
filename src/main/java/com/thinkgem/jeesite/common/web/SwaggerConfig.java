package com.thinkgem.jeesite.common.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //必须存在
@EnableSwagger2 //必须存在
@EnableWebMvc //必须存在
@ComponentScan(basePackages = {
        "com.thinkgem.jeesite.modules.test.web",
        "com.thinkgem.jeesite.modules.sys.web"
//        ,"com.thinkgem.jeesite.modules.campaign.api",
//        "com.thinkgem.jeesite.modules.info.api",
//        "com.thinkgem.jeesite.modules.notice.api",
//        "com.thinkgem.jeesite.modules.base.api",
//        "com.thinkgem.jeesite.modules.prettypictures.api",
//        "com.thinkgem.jeesite.modules.publicclass.api",
//        "com.thinkgem.jeesite.modules.sharecase.api"
}) //必须存在 扫描的API Controller package name 也可以直接扫描class (basePackageClasses)
public class SwaggerConfig{

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("qixianchuan", "", "");
        return new ApiInfoBuilder()
                .title("手机端API接口")
                .description("用于测试使用")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}

