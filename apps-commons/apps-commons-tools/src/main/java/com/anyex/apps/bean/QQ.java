package com.anyex.apps.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * QQ
 * <p>File：QQ.java</p>
 * <p>Title: QQ</p>
 * <p>Description: QQ</p>
 * <p>Copyright: Copyright (c) 2019/10/29</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class QQ implements Serializable
{
    private String appKey;
    
    private String secret;
    
    private String redirectUri;
}
