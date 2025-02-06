package com.anyex.apps.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * ZjDing
 * <p>File：ZjDing.java</p>
 * <p>Title: ZjDing</p>
 * <p>Description: ZjDing</p>
 * <p>Copyright: Copyright (c) 2019/10/24</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ZjDing implements Serializable
{
    private String domainName;

    private String protocal;

    private String tenantId;

//    private String scanAccessKey;
//
//    private String scanSecretKey;
//
//    private String appAccessKey;
//
//    private String appSecretKey;

    private String redirectUri;

    private ZjDing.Scan scan = new ZjDing.Scan();

    @Data
    public static class Scan implements Serializable
    {
        private String accessKey;

        private String secretKey;
    }

    private ZjDing.App app = new ZjDing.App();

    @Data
    public static class App implements Serializable
    {
        private String accessKey;

        private String secretKey;
    }
}
