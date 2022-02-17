package com.itchenyang.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    //    给/api/.*开头的url，分组为distributeApi
    @Bean
    public Docket adminApiConfig() {
        List<Parameter> params = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("X-token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        params.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("distributeApi")    // 分组管理
                .apiInfo(adminApiInfo())   // 为分组创建基本信息
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build()
                .globalOperationParameters(params);
    }

    public ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("分布式用户中心系统API文档")
                .description("描述了分布式用户中心的各个模块的接口调用方式")
                .contact(new Contact("ChenYang","http://cjkcare.top","1151094976@qq.com"))
                .version("1.1")
                .build();
    }
}
