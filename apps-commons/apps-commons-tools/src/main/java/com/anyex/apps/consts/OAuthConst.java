package com.anyex.apps.consts;

/**
 * OAuthConst
 * <p>File：OAuthConst.java</p>
 * <p>Title: OAuthConst</p>
 * <p>Description: OAuthConst</p>
 * <p>Copyright: Copyright (c) 2019/10/29</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public abstract class OAuthConst
{
    public static final String CODE                         = "code";
    
    /**
     * 微信公众号 认证请求地址
     */
    public static final String OAUTH_WX_AUTHORIZE_URL       = "https://open.weixin.qq.com/connect/qrconnect";

    public static final String OAUTH_WX_GET_CODE_URL        = "https://open.weixin.qq.com/connect/oauth2/authorize";
    
    public static final String OAUTH_WX_GET_TOKEN_URL       = "https://api.weixin.qq.com/sns/oauth2/access_token";
    
    public static final String OAUTH_WX_GET_INFO_URL        = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 微信小程序 认证请求地址
     */
    public static final String OAUTH_WXMP_GET_INFO_URL      = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 浙政钉小程序 认证请求地址
     */
    public static final String OAUTH_ZJDINGMP_GET_INFO_URL  = "https://openplatform.dg-work.cn";
    
    /**
     * QQ OAUTH 认证请求地址
     */
    public static final String OAUTH_QQ_AUTHORIZE_URL       = "https://graph.qq.com/oauth2.0/authorize";
    
    public static final String OAUTH_QQ_GET_TOKEN_URL       = "https://graph.qq.com/oauth2.0/token";
    
    public static final String OAUTH_QQ_GET_OPENID_URL      = "https://graph.qq.com/oauth2.0/me";
    
    public static final String OAUTH_QQ_GET_INFO_URL        = "https://graph.qq.com/user/get_user_info";
    
    /**
     * 微博 OAUTH 认证请求地址
     */
    public static final String OAUTH_WEIBO_AUTHORIZE_URL    = "https://api.weibo.com/oauth2/authorize";
    
    public static final String OAUTH_WEIBO_GET_TOKEN_URL    = "https://api.weibo.com/oauth2/access_token";
    
    public static final String OAUTH_WEIBO_GET_INFO_URL     = "https://api.weibo.com/oauth2/get_token_info";
}
