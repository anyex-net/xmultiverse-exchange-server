package com.anyex.apps.filter;

import cn.hutool.json.JSONUtil;
import com.anyex.apps.config.CSRFConfig;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.JSONUtils;
import com.anyex.apps.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * @Auther: GMY
 * @Date: 2022/09/15/19:54
 * @Description: CsrfFilter CSRF防护过滤类
 */
/**
 * 参考相关CSRF建设思路：
 * 1：前端实践：如何防止csrf制跨站请求攻击（vue代码说明）
 *      https://developer.aliyun.com/article/1178663
 * 2：前端安全系列（二）：如何防止CSRF攻击？
 *      https://tech.meituan.com/2018/10/11/fe-security-csrf.html
 */
@Slf4j
public class CsrfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @return void
     * @author GMY
     * @date 2022/9/16 9:51
     * @description 执行CSRF过滤操作
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("CSRFConfig.getCsrfProtectEnable():{}", CSRFConfig.getCsrfProtectEnable());
        // 判断CSRF防护是否开启，如果没开启则直接略过过滤操作
        if (!CSRFConfig.getCsrfProtectEnable()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String referer = request.getHeader("Referer");
            if (!StringUtils.isBlank(referer)) {
                // 获取Referer参数中的地址和端口
                String refererHostAndPort = getHostAndPort(request,referer);
                log.info("refererHostAndPort:{}", refererHostAndPort);
                // 获取RequestURL参数中的地址和端口
                String requestHostAndPort = getHostAndPort(request,null);
                log.info("requestHostAndPort:{}", requestHostAndPort);
                // 同域名和同端口，即同一个域的系统，通过
                if (requestHostAndPort.equalsIgnoreCase(refererHostAndPort)) {
                    log.info("同域名和同端口，即同一个域的系统，通过");
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    // 如果不同域名或端口，继续判断域名是否在白名单中，如果在白名单中则通过
                    if(isCsrfWhiteDomains(refererHostAndPort)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
//                    // 获取RequestURL参数中的路径信息
//                    String path = new URL(req.getRequestURL().toString()).getPath();
//                    log.info("request请求路径path:{} ", path);
//                    // 将路径中的域名去除，只保留具体路径
//                    String actionPath = path.replaceAll(servletRequest.getServletContext().getContextPath(), "");
//                    // 判断路径是否在访问路径白名单中，如果在白名单中，则通过，继续后续执行
//                    if(isCsrfWhitePaths(actionPath)) {
//                        filterChain.doFilter(servletRequest, servletResponse);
//                        return;
//                    }
                    log.error("csrf跨站点伪造请求已经被拦截,requestURL:{}, referer:{}", request.getRequestURL().toString(), referer);
                    JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
                    jsonMessage.setCode(CommonEnums.ERROR_BLACK_CSRF_LIMIT.getCode());
                    jsonMessage.setMessage("csrf跨站点伪造请求已经被拦截.");
                    jsonMessage.setTimestamp(System.currentTimeMillis());
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
//                    request.sendRedirect(request.getContextPath() + "/illegal");
                    return;
                }
            } else {
                // 需要到时处理
                log.error("csrf跨站点伪造referer为null");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * @param request
     * @param referer
     * @return java.lang.String
     * @author GMY
     * @date 2022/9/16 9:34
     * @description 获取请求地址和端口
     */
    protected String getHostAndPort(HttpServletRequest request, String referer) throws IOException {
        URL url;
        if (StringUtils.isNotEmpty(referer)) {
            url = new URL(referer);
        } else {
            url = new URL(request.getRequestURL().toString());
        }
        String requestHostAndPort;
        if(url.getPort() == -1) {
            requestHostAndPort = url.getHost();
        } else {
            requestHostAndPort = url.getHost() + ":" + url.getPort();
        }
        return requestHostAndPort;
    }

    /**
     * @param refererHostAndPort
     * @return boolean
     * @author GMY
     * @date 2022/9/16 9:52
     * @description 判断请求域名是否在域名白名单中
     */
    private boolean isCsrfWhiteDomains(String refererHostAndPort) {
        log.info("CSRFConfig.getCsrfWhiteDomains: {}", CSRFConfig.getCsrfWhiteDomains());
        if(CSRFConfig.getCsrfWhiteDomains() != null && CSRFConfig.getCsrfWhiteDomains().length > 0) {
            for (String csrfWhiteDomain : CSRFConfig.getCsrfWhiteDomains()) {
                if(!StringUtils.isBlank(csrfWhiteDomain)) {
                    if(csrfWhiteDomain.equals(refererHostAndPort)) {
                        log.info("跨站点请求所有【域名】白名单：csrfWhiteDomains = {}", JSONUtil.toJsonStr(CSRFConfig.getCsrfWhiteDomains()));
                        log.info("符合跨站点请求【域名】白名单：refererHost = {}", refererHostAndPort);
                        return true;
                    }
                }
            }
            log.error("跨站点请求非法【域名】：refererHost = {}", refererHostAndPort);
        }
        return false;
    }

    /**
     * @param path
     * @return boolean
     * @author GMY
     * @date 2022/9/16 9:52
     * @description 判断请求路径是否在路径白名单中
     */
    private boolean isCsrfWhitePaths(String path) {

        if(CSRFConfig.getCsrfWhitePaths() != null && CSRFConfig.getCsrfWhitePaths().length > 0) {
            for (String csrfWhitePath : CSRFConfig.getCsrfWhitePaths()) {
                if(!StringUtils.isBlank(csrfWhitePath)) {
                    if(csrfWhitePath.equals(path)) {
                        log.info("跨站点请求所有路径白名单：csrfWhitePaths = {}", JSONUtil.toJsonStr(CSRFConfig.getCsrfWhitePaths()));
                        log.info("符合跨站点请求路径白名单：path = {}", path);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
