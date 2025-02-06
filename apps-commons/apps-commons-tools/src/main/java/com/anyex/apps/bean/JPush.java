package com.anyex.apps.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * JPush
 * <p>File：JPush.java</p>
 * <p>Title: JPush</p>
 * <p>Description: JPush</p>
 * <p>Copyright: Copyright (c) 2019/10/24</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class JPush implements Serializable
{
    private String appKey;
    
    private String secret;
}
