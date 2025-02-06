package com.anyex.apps.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * QQCallback
 * <p>File：QQCallback.java</p>
 * <p>Title: QQCallback</p>
 * <p>Description: QQCallback</p>
 * <p>Copyright: Copyright (c) 2019/10/30</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class QQCallback implements Serializable
{
    @JsonProperty(value = "access_token")
    private String accessToken;
    
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
    
    @JsonProperty(value = "expires_in")
    private Long   expiresIn;
    
    @JsonProperty(value = "client_id")
    private String clientId;
    
    @JsonProperty(value = "openid")
    private String openid;
    
    @JsonProperty(value = "nickname")
    private String nickname;
    
    @JsonProperty(value = "figureurl_qq_2")
    private String headimgurl;
}
