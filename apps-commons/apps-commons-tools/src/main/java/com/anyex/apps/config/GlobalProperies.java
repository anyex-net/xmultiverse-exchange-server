package com.anyex.apps.config;

import com.anyex.apps.bean.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 基础配置
 *
 * @author Playguy
 */
@Data
@Configuration
@ConfigurationProperties("com.anyex")
public class GlobalProperies
{
    private String  projectName   = "apps";
    
    private String  domainName    = "apps.com";
    
    private String  emailProvider = "generic";
    
    private String  cookiePath    = "/";
    
    private Boolean cookieSecure  = false;
    
    private String  tradeUrl      = "";
    
    private String  timeFormat    = "yyyy-MM-dd HH:mm:ss";
    
    private String  passWord      = "123456@im";
    
    private boolean openLog       = false;
    
    private boolean switchRemind  = false;
    
    private boolean esEnable      = false;
    
    private String  running       = "dev";
    
    /**** 跨域设置 ****/
    private String  allowOrigin   = "*";
    
    private String  allowMethods  = "POST,GET,OPTIONS,DELETE";
    
    private String  maxAge        = "3600";
    
    private WeiXin  weixin        = new WeiXin();

    private ZjDing  zjDing        = new ZjDing();
    
    private Weibo   weibo         = new Weibo();
    
    private QQ      qq            = new QQ();
    
    private JPush jPush           = new JPush();
    
    private Aliyun aliyun         = new Aliyun();
    
    private Amazon amazon         = new Amazon();
}
