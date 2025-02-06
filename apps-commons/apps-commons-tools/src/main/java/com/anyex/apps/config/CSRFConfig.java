package com.anyex.apps.config;

import com.anyex.apps.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: GMY
 * @Date: 2022/09/16/9:54
 * @Description: CSRF防护配置工具类
 */
@Component
@ConfigurationProperties(prefix = "com.anyex.csrf")
public class CSRFConfig {
    /**
     * csrf攻击防护开关配置
     */
    public static Boolean csrfProtectEnable;

    /**
     * 跨站点请求域名白名单，通过英文逗号分隔。在application.properties配置
     */
    public static String csrfWhiteDomains;

    /**
     * 跨站点请求路径白名单，通过英文逗号分隔。在application.properties配置
     */
    public static String csrfWhitePaths;

    /**
     * @param
     * @return java.lang.Boolean
     * @author GMY
     * @date 2022/9/16 10:13
     * @description csrf攻击防护开关配置，默认为开启
     */
    public static Boolean getCsrfProtectEnable() {
        return csrfProtectEnable == null ? true : csrfProtectEnable;
    }

    /**
     * @param
     * @return java.lang.String[]
     * @author GMY
     * @date 2022/9/16 10:09
     * @description 获取请求域名白名单
     */
    public static String[] getCsrfWhiteDomains() {
        if (StringUtils.isNotEmpty(csrfWhiteDomains)) {
            return csrfWhiteDomains.split(",");
        }
        return null;
    }

    /**
     * @param
     * @return java.lang.String[]
     * @author GMY
     * @date 2022/9/16 10:07
     * @description 获取请求路径白名单
     */
    public static String[] getCsrfWhitePaths() {
        if (StringUtils.isNotEmpty(csrfWhitePaths)) {
            return csrfWhitePaths.split(",");
        }
        return null;
    }

    public void setCsrfProtectEnable(Boolean csrfProtectEnable) {
        CSRFConfig.csrfProtectEnable = csrfProtectEnable;
    }

    public void setCsrfWhiteDomains(String csrfWhiteDomains) {
        CSRFConfig.csrfWhiteDomains = csrfWhiteDomains;
    }

    public void setCsrfWhitePaths(String csrfWhitePaths) {
        CSRFConfig.csrfWhitePaths = csrfWhitePaths;
    }
}
