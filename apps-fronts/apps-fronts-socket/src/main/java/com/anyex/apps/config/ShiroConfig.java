package com.anyex.apps.config;

import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.shiro.WebSessionManager;
import com.anyex.apps.system.service.*;
import com.google.common.collect.Maps;
import com.anyex.apps.shiro.ShiroSessionManager;
import com.anyex.apps.shiro.filter.FormFilter;
import com.anyex.apps.shiro.realm.AdminAuthorizingRealm;
import com.anyex.apps.shiro.session.RedisSessionDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class ShiroConfig
{
    @Autowired
    private GlobalProperies globalProperies;

    @Bean
    public RedisSessionDAO sessionDAO(ShiroSessionManager shiroSessionManager)
    {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setShiroSessionManager(shiroSessionManager);
        sessionDAO.setSessionPrefix(CacheConst.ADMIN_SHIRO_CACHE_PREFIX);
        return sessionDAO;
    }

    @Bean
    public SimpleCookie cookie()
    {
        SimpleCookie cookie = new SimpleCookie(CacheConst.ADMIN_COOKIE_ID);
        cookie.setDomain(globalProperies.getDomainName());
        cookie.setPath(globalProperies.getCookiePath());
        cookie.setSecure(globalProperies.getCookieSecure());
        return cookie;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO, SimpleCookie cookie)
    {
        WebSessionManager sessionManager = new WebSessionManager(cookie.getName());
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(120000);
        sessionManager.setGlobalSessionTimeout(60 * 60 * 24 * 1000);
        return sessionManager;
    }

    @Bean
    public AdminAuthorizingRealm adminAuthorizingRealm(SysUserInfoService userInfoService, SysResourcesService resourcesService, SysRoleInfoService roleInfoService)
    {
        AdminAuthorizingRealm authorizingRealm = new AdminAuthorizingRealm();
        authorizingRealm.setUserInfoService(userInfoService);
        authorizingRealm.setResourcesService(resourcesService);
        authorizingRealm.setRoleInfoService(roleInfoService);
        return authorizingRealm;
    }

    @Bean
    public SecurityManager securityManager(AuthorizingRealm adminAuthorizingRealm, SessionManager sessionManager)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminAuthorizingRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 获取filters
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 将自定义 的FormAuthenticationFilter注入shiroFilter中
        filters.put("authc", new FormFilter());
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        // swagger2
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        // system
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/styles/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/login/submit/qrcode", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        //
        filterChainDefinitionMap.put("/zjding/**", "anon");
        //
        filterChainDefinitionMap.put("/websocket/**", "anon");
        //
        filterChainDefinitionMap.put("/system/user/dataTest", "anon");
        //
        filterChainDefinitionMap.put("/*/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 启动Shiro过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean shiroFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
