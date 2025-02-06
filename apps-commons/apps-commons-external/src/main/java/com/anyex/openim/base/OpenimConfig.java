package com.anyex.openim.base;

import com.anyex.apps.utils.StringUtils;
import lombok.Data;
import com.anyex.openim.constants.ApiServerType;
import com.anyex.openim.utils.CommUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class OpenimConfig {

    @Value("${openim.apiRoot}")
    private String apiApi;
    @Value("${openim.secret}")
    private String secret;
    private Integer platformId = 1;
    /**
     * 是否验证请求参数
     */
    private boolean requestParamValid=false;

    @Value("${openim.adminAccount}")
    private String adminAccount;
    @Value("${openim.adminPwd}")
    private String adminPwd;
    @Value("${openim.authKey}")
    private String authKey;

    public String getApiUrl(ApiServerType serverType) {
        if (ApiServerType.API == serverType && StringUtils.isNotEmpty(apiApi)) {
            return apiApi;
        } /*else if (ApiServerType.CHAT == serverType && StringUtils.isNotEmpty(apiChat)) {
            return apiChat;
        } else if (ApiServerType.ADMIN == serverType && StringUtils.isNotEmpty(apiAdmin)) {
            return apiAdmin;
        }
        return CommUtils.appendUrl(api, serverType.getApiPath());*/
        return CommUtils.appendUrl(apiApi, serverType.getApiPath());
    }
}
