package com.anyex.apps.config;

import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.shiro.WebSessionManager;
import com.anyex.apps.shiro.realm.AppAuthorizingRealm;
import com.anyex.apps.shiro.realm.WebAuthorizingRealm;
import com.google.common.collect.Maps;
import com.anyex.apps.shiro.ShiroSessionManager;
import com.anyex.apps.shiro.filter.FormFilter;
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

    @Autowired(required = false)
    private AccountService  accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @Bean
    public WebAuthorizingRealm authorizingRealm()
    {
        WebAuthorizingRealm authorizingRealm = new WebAuthorizingRealm();
        authorizingRealm.setAccountService(accountService);
        authorizingRealm.setMsgRecordService(msgRecordService);
        return authorizingRealm;
    }

    @Bean
    public RedisSessionDAO sessionDAO(ShiroSessionManager shiroSessionManager)
    {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setShiroSessionManager(shiroSessionManager);
        sessionDAO.setSessionPrefix(CacheConst.WEB_SHIRO_CACHE_PREFIX);
        return sessionDAO;
    }

    @Bean
    public SimpleCookie cookie()
    {
        SimpleCookie cookie = new SimpleCookie(CacheConst.WEB_IM_ID);
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
        //sessionManager.setGlobalSessionTimeout(60 * 60 * 24 * 7 * 30 * 1000); // 30周
        sessionManager.setGlobalSessionTimeout(60 * 60 * 24 * 7 * 1000); // 7天
        //sessionManager.setGlobalSessionTimeout(-1); // 永不超时
        return sessionManager;
    }

//    @Bean
//    public AppAuthorizingRealm appAuthorizingRealm(AccountService accountService, MsgRecordService msgRecordService)
//    {
//        AppAuthorizingRealm authorizingRealm = new AppAuthorizingRealm();
//        authorizingRealm.setAccountService(accountService);
//        authorizingRealm.setMsgRecordService(msgRecordService);
//        return authorizingRealm;
//    }

    @Bean
    public SecurityManager securityManager(AuthorizingRealm appAuthorizingRealm, SessionManager sessionManager)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(appAuthorizingRealm);
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
        filterChainDefinitionMap.put("/auth/register/**", "anon");
        filterChainDefinitionMap.put("/auth/login/**", "anon");
        filterChainDefinitionMap.put("/auth/forget/**", "anon");
        filterChainDefinitionMap.put("/auth/logoff/**", "anon");
        //
        filterChainDefinitionMap.put("/common/**", "anon");
        //
        filterChainDefinitionMap.put("/openim/**", "anon");
        // 支付回调
        filterChainDefinitionMap.put("/payment/wivpay/in/notify**", "anon"); // wivpay 代收业务 支付状态回调
        filterChainDefinitionMap.put("/payment/wivpay/in/redirect**", "anon"); // wivpay 代收业务 redirect
        filterChainDefinitionMap.put("/payment/globalpay/out/notify**", "anon"); // globalpay 代付业务  支付状态回调
        //
        filterChainDefinitionMap.put("/operation/appDownloadInfo/downloadClick", "anon");
        filterChainDefinitionMap.put("/operation/appActivationInfo/activation", "anon");

        // business
        // 一元夺宝、半价购买
        filterChainDefinitionMap.put("/business/luckybox/activity/activityTreasureHunt/data", "anon");
        filterChainDefinitionMap.put("/business/luckybox/activity/activityTreasureHunt/playerData", "anon");
        filterChainDefinitionMap.put("/business/luckybox/activity/activityTreasureHunt/findBy", "anon");
        filterChainDefinitionMap.put("/business/luckybox/activity/activityHotDeals/data", "anon");
        filterChainDefinitionMap.put("/business/luckybox/activity/activityHotDeals/playerData", "anon");
        filterChainDefinitionMap.put("/business/luckybox/activity/activityHotDeals/findBy", "anon");
        // 游戏大转盘
        filterChainDefinitionMap.put("/business/luckybox/game/game/gameList", "anon");
        filterChainDefinitionMap.put("/business/luckybox/game/game/gamePlayerData", "anon");
        filterChainDefinitionMap.put("/business/luckybox/game/gamePrize/findBy", "anon");
        filterChainDefinitionMap.put("/business/luckybox/game/gamePrize/gamePrizeList", "anon");
        //
        filterChainDefinitionMap.put("/business/luckybox/order/order4Activity/data", "anon");
        filterChainDefinitionMap.put("/business/luckybox/order/order4Game/data", "anon");
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
