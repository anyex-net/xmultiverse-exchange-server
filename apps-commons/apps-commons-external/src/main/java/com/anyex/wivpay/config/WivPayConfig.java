package com.anyex.wivpay.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 支付配置
 *
 * @author Playguy
 */
@Data
@Component
@ConfigurationProperties("com.anyex.wivpay")
public class WivPayConfig
{

    // 拉取支付链接网关地址
    private String  payUrl   = "";

    // 支付结果回调业务系统地址
    private String  notifyUrl    = "";

    private String  redirectUrl    = "";

    // 支付查询接口
    private String  payQueryUrl = "";
    
    private String  merchantSn = "";
    
    private String  merchantAk    = "";

    private String  merchantSk      = "";

    private String  channelAliasName      = "testEp";

}
