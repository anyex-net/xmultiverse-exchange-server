package com.anyex.apps.shiro.auth;

import com.anyex.apps.account.model.QQCallback;
import com.anyex.apps.account.model.WeiXinCallback;
import com.anyex.apps.account.model.WeiXinMPCallback;
import com.anyex.apps.account.model.WeiboCallback;
import com.anyex.apps.bean.Encodes;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.consts.OAuthConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.HttpUtils;
import com.anyex.apps.utils.JSONUtils;
import com.anyex.apps.utils.StringUtils;
import com.google.common.collect.Maps;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * OauthClient
 * <p>File：OauthClient.java</p>
 * <p>Title: OauthClient</p>
 * <p>Description: OauthUtil</p>
 * <p>Copyright: Copyright (c) 2019/10/30</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Setter
public class OauthClient
{
    private GlobalProperies properies;
    
    /**
     * 取微信访问凭证
     * @param code
     * <p>
     *     {
     *      "access_token":"ACCESS_TOKEN",
     *      "expires_in":7200,
     *      "refresh_token":"REFRESH_TOKEN",
     *      "openid":"OPENID",
     *      "scope":"SCOPE",
     *      "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     * </p>
     * @return {@link WeiXinCallback}
     * @throws BusinessException
     */
    public WeiXinCallback getWXAccessToken(String code) throws BusinessException
    {
        //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", properies.getWeixin().getOpen().getAppKey());
        params.put("secret", properies.getWeixin().getOpen().getSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String response = HttpUtils.get(OAuthConst.OAUTH_WX_GET_TOKEN_URL, params);
        log.info("getWXAccessToken response:{}", response);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, WeiXinCallback.class);
    }
    
    /**
     * 取微信中的用户基础信息
     * @param accessToken
     * @param openId
     * @return {@link WeiXinCallback}
     */
    public WeiXinCallback getWXUserInfo(String accessToken, String openId)
    {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        params.put("openId", openId);
        String response = HttpUtils.get(OAuthConst.OAUTH_WX_GET_INFO_URL, params);
        log.info("getWXUserInfo response:{}", response);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, WeiXinCallback.class);
    }

    /**
     * 取微信小程序中的用户基础信息
     * @param jscode
     * @return {@link WeiXinCallback}
     */
    public WeiXinMPCallback getWXMPUserInfo(String jscode)
    {
        //GET https://api.weixin.qq.com/sns/jscode2session
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", properies.getWeixin().getOpen().getAppKey());
        params.put("secret", properies.getWeixin().getOpen().getSecret());
        params.put("js_code", jscode);
        params.put("grant_type", "authorization_code");
        String response = HttpUtils.get(OAuthConst.OAUTH_WXMP_GET_INFO_URL, params);
        log.info("getWXMPUserInfo response:{}", response);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        WeiXinMPCallback weiXinMPCallback = JSONUtils.jsonToBean(response, WeiXinMPCallback.class);
        //WeiXinMPCallback(session_key=null, unionid=null, openid=null)
        if (null == weiXinMPCallback.getOpenid()) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return weiXinMPCallback;
    }

    /**
     * 取浙政钉小程序中的用户基础信息
     * @param code
     * @return {@link ZjDingAppUserInfo}
     */
//    public ZjDingAppUserInfo getZJDINGMPUserInfo(String code) throws IOException {
//        ZjDingUtil zjDingUtil = new ZjDingUtil(properies.getZjDing().getApp().getAccessKey(), properies.getZjDing().getApp().getSecretKey(),
//                properies.getZjDing().getDomainName(), properies.getZjDing().getProtocal());
//        ZjDingTokenInfo zjDingTokenInfo = zjDingUtil.getAccessToken();
//        log.info("getZJDINGMPUserInfo zjDingTokenInfo response:{}", zjDingTokenInfo);
//        ZjDingAppUserInfo zjDingAppUserInfo = zjDingUtil.getUserInfo(zjDingTokenInfo.getAccessToken(), code);
//        log.info("getZJDINGMPUserInfo zjDingAppUserInfo response:{}", zjDingAppUserInfo);
//        if (null == zjDingAppUserInfo) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
//        return zjDingAppUserInfo;
//    }
    
    /**
     * 取QQ的访问凭证
     * @param code
     * <p>
     *   access_token	授权令牌，Access_Token。
     *   expires_in	    该access token的有效期，单位为秒。
     *   refresh_token	在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。
     * </p>
     * @return {@link QQCallback}
     * @throws BusinessException
     */
    public QQCallback getQQAccessToken(String code) throws BusinessException
    {
        Map<String, String> params = Maps.newHashMap();
        params.put("grant_type", "authorization_code");
        params.put("client_id", properies.getQq().getAppKey());
        params.put("client_secret", properies.getQq().getSecret());
        params.put("redirect_uri", Encodes.urlEncode(properies.getQq().getRedirectUri()));
        params.put("code", code);
        String response = HttpUtils.get(OAuthConst.OAUTH_QQ_GET_TOKEN_URL, params);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, QQCallback.class);
    }
    
    /**
     * 取QQ中的OPENID
     * <p>
     *     callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
     * </p>
     * @param accessToken
     * @return {@link QQCallback}
     */
    public QQCallback getQQOpenId(String accessToken)
    {
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        String response = HttpUtils.get(OAuthConst.OAUTH_QQ_GET_OPENID_URL, params);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, QQCallback.class);
    }
    
    /**
     * 取QQ中的用户基础信息
     * @param accessToken
     * @return {@link QQCallback}
     */
    public QQCallback getQQUserInfo(String accessToken, String openId)
    {
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        params.put("oauth_consumer_key", properies.getQq().getAppKey());
        String response = HttpUtils.get(OAuthConst.OAUTH_QQ_GET_INFO_URL, params);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, QQCallback.class);
    }
    
    /**
     * 取微博的访问凭证
     * @param code
     * @return {@link WeiboCallback}
     * @throws BusinessException
     */
    public WeiboCallback getWBAccessToken(String code) throws BusinessException
    {
        Map<String, String> params = Maps.newHashMap();
        params.put("client_id", properies.getWeibo().getAppKey());
        params.put("client_secret", properies.getQq().getSecret());
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("redirect_uri", Encodes.urlEncode(properies.getWeibo().getRedirectUri()));
        String response = HttpUtils.get(OAuthConst.OAUTH_WEIBO_GET_TOKEN_URL, params);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, WeiboCallback.class);
    }
    
    /**
     * 取QQ中的用户基础信息
     * @param accessToken
     * @return {@link WeiboCallback}
     */
    public WeiboCallback getWBUserInfo(String accessToken)
    {
        Map<String, String> params = Maps.newHashMap();
        params.put("access_token", accessToken);
        String response = HttpUtils.get(OAuthConst.OAUTH_WEIBO_GET_INFO_URL, params);
        if (StringUtils.isBlank(response)) throw new BusinessException(CommonEnums.ERROR_AUTHER_FAILED);
        return JSONUtils.jsonToBean(response, WeiboCallback.class);
    }
}
