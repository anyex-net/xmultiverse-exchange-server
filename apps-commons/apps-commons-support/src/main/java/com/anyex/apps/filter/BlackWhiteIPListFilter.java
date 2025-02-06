package com.anyex.apps.filter;

import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.JSONUtils;
import com.anyex.apps.utils.NetworkUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class BlackWhiteIPListFilter implements Filter {

    private List<String> blackList = Arrays.asList("blocked-ip1", "blocked-ip2");
    private List<String> whiteList = Arrays.asList("127.0.0.1", "0:0:0:0:0:0:0:1", "112.14.45.73");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //
        String clientIP = NetworkUtils.getIpAddr((HttpServletRequest) request);

        // 检查黑名单
        if (blackList.contains(clientIP)) {
            log.error("黑名单IP:{}", clientIP);
            //response.getWriter().write("Your IP is blackListed.");
            //return;
            // 改造
            JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
            jsonMessage.setCode(CommonEnums.ERROR_BLACK_WHITE_IP_LIST.getCode());
            jsonMessage.setMessage("Your IP is blackListed.");
            jsonMessage.setTimestamp(System.currentTimeMillis());
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
            return;
        }

        // 检查白名单
        if (!whiteList.contains(clientIP)) {
            log.error("非白名单IP:{}", clientIP);
            //response.getWriter().write("Your IP is not whiteListed.");
            //return;
            JsonMessage<Object> jsonMessage = new JsonMessage<Object>();
            jsonMessage.setCode(CommonEnums.ERROR_BLACK_WHITE_IP_LIST.getCode());
            jsonMessage.setMessage("Your IP is not whiteListed.");
            jsonMessage.setTimestamp(System.currentTimeMillis());
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSONUtils.beanToJson(jsonMessage));
            return;
        }

        chain.doFilter(request, response);
    }

    // 可以实现 init 和 destroy 方法，根据需要进行处理
}
