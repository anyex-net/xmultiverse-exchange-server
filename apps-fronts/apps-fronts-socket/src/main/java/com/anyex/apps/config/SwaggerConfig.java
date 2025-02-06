package com.anyex.apps.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开启Swagger2配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)//
                .select()//
                .apis(RequestHandlerSelectors.basePackage("com.anyex"))//
                .paths(PathSelectors.ant("/**"))//
                .build()//
                .apiInfo(apiInfo())//
                .groupName("所有api")
                .useDefaultResponseMessages(false)//
                .globalResponseMessage(RequestMethod.GET, //
                        Lists.newArrayList(//
                                new ResponseMessageBuilder().code(500).message("500 message")//
                                        .responseModel(new ModelRef("Error")).build(), //
                                new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build()//
                        ));
        return docket;
    }

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("socket-api")//
                .description("socket api docs")//
                .termsOfServiceUrl("https://www.anyex.com")//
                .version("1.0")//
                .contact(new Contact("anyex", "https://www.anyex.com", "anyex@qq.com"))//
                .build();
    }
}
