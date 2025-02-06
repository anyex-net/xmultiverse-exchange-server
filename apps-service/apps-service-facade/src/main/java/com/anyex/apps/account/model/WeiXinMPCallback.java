package com.anyex.apps.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信小程序OAUTH认证返回对象
 */
@Data
@ApiModel(description = "微信小程序OAUTH认证返回对象")
public class WeiXinMPCallback implements Serializable
{
    @JsonProperty(value = "session_key")
    private String session_key;
    
    @JsonProperty(value = "unionid")
    private String unionid;
    
    @JsonProperty(value = "openid")
    private String openid;
}
