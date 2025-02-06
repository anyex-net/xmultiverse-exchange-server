//package com.anyex.apps.shiro.filter;
//
//import com.anyex.apps.account.enums.LoginEnums;
//import com.anyex.apps.shiro.model.AccountToken;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
//import org.apache.shiro.web.util.WebUtils;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * ZjDingFilter
// * <p>File：ZjDingFilter.java</p>
// * <p>Title: ZjDingFilter</p>
// * <p>Description: ZjDingFilter</p>
// * <p>Copyright: Copyright (c) 2019/10/30</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Slf4j
//@Setter
//public class ZjDingFilter extends AuthenticatingFilter
//{
//    private String authcCode;
//
//    private String failureUrl;
//
//    @Override
//    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception
//    {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String code = httpRequest.getParameter(authcCode);
//        log.info("zjDingFilter = " + code);
//        AccountToken accountToken = new AccountToken();
//        accountToken.setLoginType(LoginEnums.ZJDINGMP.getCode());
//        accountToken.setAuthCode(code);
//        return accountToken;
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
//    {
//        String error = request.getParameter("error");
//        if (!StringUtils.isEmpty(error))
//        {
//            WebUtils.issueRedirect(request, response, failureUrl + "?error=" + error);
//            return false;
//        }
//        Subject subject = getSubject(request, response);
//        log.info("subject.isAuthenticated() == " + subject.isAuthenticated());
//        if (!subject.isAuthenticated())
//        {
//            if (StringUtils.isEmpty(request.getParameter(authcCode)))
//            {
//                // 如果没有身份认证，且没有authCode,则重定向到服务端授权
//                saveRequestAndRedirectToLogin(request, response);
//                return false;
//            }
//        }
//        return executeLogin(request, response);
//    }
//
//    @Override
//    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response)
//    {
//        Subject subject = getSubject(request, response);
//        log.info("是否验证：subject.isAuthenticated() == " + subject.isAuthenticated());
//        log.info("是否记住：subject.isRemembered() == " + subject.isRemembered());
//        if (subject.isAuthenticated() || subject.isRemembered())
//        {
//            // 重定向到成功页面
//            try
//            {
//                log.info("重定向到成功页面success。。。。");
//                issueSuccessRedirect(request, response);
//            }
//            catch (Exception e1)
//            {
//                log.info("重定向到成功页面异常=", e1);
//            }
//        }
//        else
//        {
//            try
//            {
//                log.info("重定向到失败页面failure。。。。");
//                WebUtils.issueRedirect(request, response, failureUrl);
//            }
//            catch (IOException e1)
//            {
//                log.info("重定向到失败页面异常=", e1);
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception
//    {
//        WebUtils.issueRedirect(request, response, getSuccessUrl());
//        return false;
//    }
//
//    public ZjDingFilter(String authcCode)
//    {
//        this.authcCode = authcCode;
//    }
//}
