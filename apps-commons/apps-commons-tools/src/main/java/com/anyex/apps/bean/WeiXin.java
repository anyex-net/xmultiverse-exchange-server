package com.anyex.apps.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * WeiXin
 * <p>File：WeiXin.java</p>
 * <p>Title: WeiXin</p>
 * <p>Description: WeiXin</p>
 * <p>Copyright: Copyright (c) 2019/10/24</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class WeiXin implements Serializable
{
    private String appKey;
    
    private String secret;
    
    private String redirectUri;
    
    private Open   open = new Open();
    
    @Data
    public static class Open implements Serializable
    {
        private String appKey;
        
        private String secret;
    }
}
