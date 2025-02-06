package com.anyex.apps;

import com.alipay.sofa.rpc.common.RpcOptions;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * AppsQuartzServer
 * <p>File：AppsQuartzServer.java</p>
 * <p>Title: AppsQuartzServer</p>
 * <p>Description: AppsQuartzServer</p>
 * <p>Copyright: Copyright (c) 2019/10/25</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = "com.anyex")
@ImportResource("classpath*:sofa-reference.xml")
@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class, MongoAutoConfiguration.class})
public class AppsQuartzServer
{
    public static void main(String[] args)
    {
        // 禁用链路跟踪日志
        System.setProperty(RpcOptions.DEFAULT_TRACER, "");
        SpringApplication springApplication = new SpringApplication(AppsQuartzServer.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
