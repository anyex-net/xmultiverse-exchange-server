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
//    @Bean
//    public Docket api() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)//
//                .select()//
//                .apis(RequestHandlerSelectors.basePackage("com.anyex"))//
//                .paths(PathSelectors.ant("/**"))//
//                .build()//
//                .apiInfo(apiInfo())//
//                .groupName("所有api")
//                .useDefaultResponseMessages(false)//
//                .globalResponseMessage(RequestMethod.GET, //
//                        Lists.newArrayList(//
//                                new ResponseMessageBuilder().code(500).message("500 message")//
//                                        .responseModel(new ModelRef("Error")).build(), //
//                                new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build()//
//                        ));
//        return docket;
//    }

    @Bean
    public Docket web_api_common() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("A-通用管理API")
                        .description("A-通用管理")
                        .termsOfServiceUrl("")//这里可以是项目地址
                        .version("2.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/common/**"))
                .build()
                .groupName("A-通用管理")
                .pathMapping("/");
    }

    @Bean
    public Docket web_api_auth() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("A-安全认证API")
                        .description("A-安全认证")
                        .termsOfServiceUrl("")//这里可以是项目地址
                        .version("2.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/auth/**"))
                .build()
                .groupName("A-安全认证")
                .pathMapping("/");
    }

    @Bean
    public Docket web_api_user() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("B-用户管理API")
                        .description("B-用户管理")
                        .termsOfServiceUrl("")//这里可以是项目地址
                        .version("2.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .groupName("B-用户管理")
                .pathMapping("/");
    }

    @Bean
    public Docket web_api_fund() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("B-资金管理API")
                        .description("B-资金管理")
                        .termsOfServiceUrl("")//这里可以是项目地址
                        .version("2.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/fund/**"))
                .build()
                .groupName("B-资金管理")
                .pathMapping("/");
    }

    @Bean
    public Docket web_api_rwa() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("T-RWA交易管理API")
                        .description("T-RWA交易管理")
                        .termsOfServiceUrl("")//这里可以是项目地址
                        .version("2.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/rwa/**"))
                .build()
                .groupName("T-RWA交易管理")
                .pathMapping("/");
    }

    @Bean
    public Docket web_api_spot() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("T-现货交易管理API")
                        .description("T-现货交易管理")
                        .termsOfServiceUrl("")//这里可以是项目地址
                        .version("2.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/spot/**"))
                .build()
                .groupName("T-现货交易管理")
                .pathMapping("/");
    }

//
//    @Bean
//    public Docket web_api_openim() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("IM管理API")
//                        .description("IM管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/openim/**"))
//                .build()
//                .groupName("IM管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_social() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("社交管理API")
//                        .description("社交管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/social/**"))
//                .build()
//                .groupName("社交管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_account() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("账户管理API")
//                        .description("账户管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/account/**"))
//                .build()
//                .groupName("账户管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_asset() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("资产管理API")
//                        .description("资产管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/asset/**"))
//                .build()
//                .groupName("资产管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_payment() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("充值提现API")
//                        .description("充值提现")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/payment/**"))
//                .build()
//                .groupName("充值提现")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_operation() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("运营管理API")
//                        .description("运营管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/operation/**"))
//                .build()
//                .groupName("运营管理")
//                .pathMapping("/");
//    }
//
////    @Bean
////    public Docket web_api_business_luckybox_shop() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .apiInfo(new ApiInfoBuilder()
////                        .title("业务luckybox店铺管理API")
////                        .description("业务luckybox店铺管理")
////                        .termsOfServiceUrl("")//这里可以是项目地址
////                        .version("2.0.1")
////                        .build())
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.ant("/business/luckybox/shop/**"))
////                .build()
////                .groupName("业务luckybox店铺管理")
////                .pathMapping("/");
////    }
//
//    @Bean
//    public Docket web_api_business_luckybox_goods() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("业务luckybox商品管理API")
//                        .description("业务luckybox商品管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/business/luckybox/goods/**"))
//                .build()
//                .groupName("业务luckybox商品管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_business_luckybox_activity() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("业务luckybox活动管理API")
//                        .description("业务luckybox活动管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/business/luckybox/activity/**"))
//                .build()
//                .groupName("业务luckybox活动管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_business_luckybox_game() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("业务luckybox游戏管理API")
//                        .description("业务luckybox游戏管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/business/luckybox/game/**"))
//                .build()
//                .groupName("业务luckybox游戏管理")
//                .pathMapping("/");
//    }
//
//    @Bean
//    public Docket web_api_business_luckybox_order() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("业务luckybox订单管理API")
//                        .description("业务luckybox订单管理")
//                        .termsOfServiceUrl("")//这里可以是项目地址
//                        .version("2.0.1")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/business/luckybox/order/**"))
//                .build()
//                .groupName("业务luckybox订单管理")
//                .pathMapping("/");
//    }

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("web-api")//
                .description("web api docs")//
                .termsOfServiceUrl("https://www.anyex.com")//
                .version("1.0")//
                .contact(new Contact("anyex", "https://www.anyex.com", "anyex@qq.com"))//
                .build();
    }
}
