//package com.anyex.apps;
//
//import com.alipay.sofa.rpc.common.RpcOptions;
//import lombok.extern.slf4j.Slf4j;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.Banner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Slf4j
//@Configuration
//@EnableCaching
//@EnableAsync
//@EnableScheduling
//@EnableTransactionManagement
////@ImportResource("classpath*:content.xml")
//@ComponentScan("com.anyex")
//@MapperScan(basePackages = {"com.anyex.apps.**.mapper"})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, MongoAutoConfiguration.class})
//public class AppsAdminSingleServer
//{
//    public static void main(String[] args)
//    {
//        // 禁用链路跟踪日志
//        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
//        SpringApplication springApplication = new SpringApplication(AppsAdminSingleServer.class);
//        springApplication.setBannerMode(Banner.Mode.CONSOLE);
//        springApplication.run(args);
//    }
//}
