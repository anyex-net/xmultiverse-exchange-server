//package com.anyex.apps;
//
//import org.springframework.context.annotation.Configuration;
//
///**
// * 初始化参数配置
// * <p>File: SystemConfig.java </p>
// * <p>Title: SystemConfig </p>
// * <p>Description: SystemConfig </p>
// * <p>Copyright: Copyright (c) 2019-02-01</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Configuration
//public class SystemConfig
//{
//    public SystemConfig()
//    {
//        /**
//         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
//         * 解决netty冲突后初始化client时还会抛出异常
//         * java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
//         */
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
//    }
//}
