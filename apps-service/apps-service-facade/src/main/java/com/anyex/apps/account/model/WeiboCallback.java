package com.anyex.apps.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * WeiboCallback
 * <p>File：WeiboCallback.java</p>
 * <p>Title: WeiboCallback</p>
 * <p>Description: WeiboCallback</p>
 * <p>Copyright: Copyright (c) 2019/10/30</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class WeiboCallback implements Serializable
{
    @JsonProperty(value = "access_token")
    private String accessToken;
    
    @JsonProperty(value = "expires_in")
    private String expiresIn;
    
    @JsonProperty(value = "uid")
    private String uid;
}
