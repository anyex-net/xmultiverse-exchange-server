package com.anyex.apps.bean;

import lombok.Data;

/**
 * 阿里云参数配置
 * <p>File: Aliyun.java </p>
 * <p>Title: Aliyun </p>
 * <p>Description: Aliyun </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class Aliyun
{
    /**
     * 密钥ID
     */
    private String accessKey;
    
    /**
     * 密钥
     */
    private String secretKey;
    
    /**
     * 应用ID
     */
    private String appKey;
    
    /**
     * 终端地址
     */
    private String endPoint;
    
    private Bucket bucket = new Bucket();
}
