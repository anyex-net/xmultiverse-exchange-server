package com.anyex.apps;

import com.alipay.sofa.rpc.common.RpcOptions;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.redis.RedisProperties;
import com.anyex.apps.utils.JpushUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableCaching
@EnableAsync
@EnableTransactionManagement
@ComponentScan("com.anyex")
@ImportResource({"classpath*:sofa-provider.xml"})
@EnableConfigurationProperties(RedisProperties.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, MongoAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.anyex.apps.config")
@MapperScan(basePackages = {"com.anyex.apps.**.mapper"})
public class AppsRPCServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
        SpringApplication springApplication = new SpringApplication(AppsRPCServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
    
    /**
     * 极光推送服务
     * @param properies
     * @return
     */
    @Bean
    public JpushUtils jpushUtils(GlobalProperies properies)
    {
        JpushUtils jpush = new JpushUtils();
        jpush.setProperies(properies);
        return jpush;
    }
}
