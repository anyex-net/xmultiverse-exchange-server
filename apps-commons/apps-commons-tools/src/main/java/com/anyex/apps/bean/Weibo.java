package com.anyex.apps.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Weibo
 * <p>File：Weibo.java</p>
 * <p>Title: Weibo</p>
 * <p>Description: Weibo</p>
 * <p>Copyright: Copyright (c) 2019/10/29</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class Weibo implements Serializable
{
    private String appKey;
    
    private String secret;
    
    private String redirectUri;
}
