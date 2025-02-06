package com.anyex.apps.config;

import com.anyex.apps.interceptor.AccessLimitHandlerInterceptor;
import com.anyex.apps.interceptor.IPLimitHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LimitConfig implements WebMvcConfigurer
{
    @Autowired
    private IPLimitHandlerInterceptor limitHandlerInterceptor;

    @Autowired
    private AccessLimitHandlerInterceptor accessLimitHandlerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(accessLimitHandlerInterceptor)//
                .addPathPatterns("/**") //
                .excludePathPatterns( //
                        "/error/**", //
                        "/static/**", //
                        "/webjars/**", //
                        "/v2/api-docs", //
                        "/api/doc.html",
                        "/swagger/**", //
                        "/swagger-ui.html", //
                        "/swagger-resources/**", //
                        "/scripts/**", //
                        "/styles/**", //
                        "/images/**"//
                );
    }
}
