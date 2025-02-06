//package com.anyex.apps.interceptor;
//
//import com.blocain.exchange.annotation.CSRFToken;
//import com.blocain.exchange.consts.BitmsConst;
//import com.blocain.exchange.consts.GlobalConst;
//import com.blocain.exchange.csrf.CSRFTokenManager;
//import com.blocain.exchange.utils.EncryptUtils;
//import com.blocain.exchange.utils.IPUtil;
//import com.blocain.exchange.utils.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.PatternMatchUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * <p>File：CSRFHandlerInterceptor</p>
// * <p>Title: </p>
// * <p>Description: 用于配合 spring <form:form> 标签防止 CSRF 攻击 </form:form></p>
// * <p>Copyright: Copyright (c) 2014 2014/3/22 10:52</p>
// * <p>Company: BloCain</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Slf4j
//@Component
//public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter
//{
//    @Autowired
//    private CSRFTokenManager csrfTokenManager;
//
//    public static final String      BOSS_RUNNER_STATUS  = "production";
//
//    public static final String      BOSS_PROJECT_NAME   = "BOSS";
//
//    protected static final String[] blackUrlPathPattern = new String[]{"*220.189.223.218*", "*221.12.40.19*", //公网专线
//                                                                        "172.31.94.173", "172.31.94.175", // Exchange
//                                                                        "172.31.94.164", "172.31.94.172", "172.31.94.163", "172.31.94.171" //QUANT
//                                                            };
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
//    {
//        if (BitmsConst.BITMS_PROJECT_NAME.equalsIgnoreCase(BOSS_PROJECT_NAME) // 支撑系统
//                && BOSS_RUNNER_STATUS.equals(BitmsConst.RUNNING_ENVIRONMONT) // 生产环境
//                && !isAccessAllowed(request))// 允许的IP地址
//        {// 审核支撑系统访问权限
//            log.error("审核支撑系统访问权限  不允许的IP地址访问 the requested resource is not available.");
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "the requested resource is not available.");
//            return false;
//        }
//        if (!request.getMethod().equalsIgnoreCase(GlobalConst.POST))
//        {// 非POST请求直接放行
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        CSRFToken csrfToken = method.getAnnotation(CSRFToken.class);
//        // 如果配置了校验csrf token校验，则校验
//        if (null != csrfToken && csrfToken.check())
//        {
//            String requestToken = CSRFTokenManager.getTokenFromRequest(request);
//            if (StringUtils.isBlank(requestToken) || requestToken.contains(CSRFTokenManager.CSRF_TOKEN_NAME))
//            {
//                log.error("Bad or missing CSRF value.");
//                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
//                return false;
//            }
//            boolean flag = csrfCheck(request, response, requestToken);
//            if (!flag)
//            {// 加入非法请求限制
//                log.error("Bad or missing CSRF value.");
//                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
//            }
//            return flag;
//        }
//        return true;
//    }
//
//    /**
//     * 判断是否有权限访问,有返回true,反之false
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    boolean isAccessAllowed(HttpServletRequest request)
//    {
//        String requestIp = IPUtil.getOriginalIpAddr(request);
//        for (String pattern : blackUrlPathPattern)
//        {
//            if (PatternMatchUtils.simpleMatch(pattern, requestIp)) { return true; }
//        }
//        return false;
//    }
//
//    /**
//     * 校验CSRF
//     * @param request
//     * @param response
//     * @param requestToken
//     * @return
//     */
//    boolean csrfCheck(HttpServletRequest request, HttpServletResponse response, String requestToken)
//    {
//        boolean flag = true;
//        String decrypt = EncryptUtils.desDecrypt(requestToken);
//        String formId = decrypt.substring(0, decrypt.indexOf(GlobalConst.SEPARATOR));
//        String sessionToken = csrfTokenManager.getTokenForRequest(request, formId);
//        if (StringUtils.equals(sessionToken, requestToken))
//        {// 验证成功之后清除TOKEN
//            csrfTokenManager.removeTokenForRequest(request, formId);
//            response.addHeader(CSRFTokenManager.CSRF_TOKEN_NAME, csrfTokenManager.getTokenForRequest(request, formId));
//        }
//        else
//        {
//            flag = false;
//            response.addHeader(CSRFTokenManager.CSRF_TOKEN_NAME, sessionToken);
//        }
//        return flag;
//    }
//}
