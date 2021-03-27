package com.example.emos.wx.confige;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：肖航
 * @description：Swagger 是一个体系有一系列项目和工具，可以做到生成各种格式的接口文档
 * @date ：2021/3/26 22:35
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

//        ApiInfoBuilder 用于在Swagger界面上添加各种信息
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("EMOS在线办公系统");
        ApiInfo info = builder.build();
        docket.apiInfo(info);

        //ApiSelectorBuilder 用来设置哪些类中的方法会生成REST API中
        ApiSelectorBuilder selectorBuilder=docket.select();
        selectorBuilder.paths(PathSelectors.any());//创建的类路径为：所有
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));//Class must have Annotation "ApiOperation.class"
        docket = selectorBuilder.build();

        /**
         * 下面的语句是开启对的支持,当用户用 Swagger调用受]认证保护的方法
         * 必须要先提交参数(例如令牌)
         */

        //存储用户必须提交的参数
        List<ApiKey> apikey = new ArrayList();
        //规定用户需要输入什么参数
        apikey.add(new ApiKey("token","token","header"));
        docket.securitySchemes(apikey);
        
        //如果用户通过JWT认证，则在Swagger中全局有效
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopeArray = {scope};
        //存储令牌和作用域
        SecurityReference reference = new SecurityReference("token", scopeArray);
        List refLisst = new ArrayList();
        refLisst.add(reference);
        SecurityContext context = SecurityContext.builder().securityReferences(refLisst).build();
        List cxtList = new ArrayList<>();
        cxtList.add(context);
        docket.securityContexts(cxtList);

        return docket;
    }
}
