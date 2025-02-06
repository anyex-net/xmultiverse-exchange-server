package com.anyex.apps.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信OAUTH认证返回对象
 */
@Data
@ApiModel(description = "微信OAUTH认证返回对象")
public class WeiXinCallback implements Serializable
{
    @JsonProperty(value = "unionid")
    private String unionid;
    
    @JsonProperty(value = "openid")
    private String openid;
    
    @JsonProperty(value = "access_token")
    private String accessToken;
    
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
    
    @JsonProperty(value = "nickname")
    private String nickName;
    
    @JsonProperty(value = "headimgurl")
    private String headimgurl;
    
    @JsonProperty(value = "expires_in")
    private Long   expiresIn;
}
