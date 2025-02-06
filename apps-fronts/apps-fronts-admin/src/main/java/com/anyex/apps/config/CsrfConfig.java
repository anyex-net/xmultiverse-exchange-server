//package com.anyex.apps.config;
//
//import com.blocain.exchange.interceptor.CSRFHandlerInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * CsrfConfig
// * <p>File: CsrfConfig.java </p>
// * <p>Title: CsrfConfig </p>
// * <p>Description: CsrfConfig </p>
// * <p>Copyright: Copyright (c) 2019-01-30</p>
// * <p>Company: BloCain</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Component
//public class CsrfConfig implements WebMvcConfigurer
//{
//    @Autowired
//    private CSRFHandlerInterceptor csrfHandlerInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry.addInterceptor(csrfHandlerInterceptor) //
//                .addPathPatterns("/**") //
//                .excludePathPatterns( //
//                        "/error/**", //
//                        "/static/**", //
//                        "/webjars/**", //
//                        "/v2/api-docs", //
//                        "/swagger/**", //
//                        "/swagger-ui.html", //
//                        "/swagger-resources/**", //
//                        "/scripts/**", //
//                        "/styles/**", //
//                        "/images/**"//
//                );
//    }
//}