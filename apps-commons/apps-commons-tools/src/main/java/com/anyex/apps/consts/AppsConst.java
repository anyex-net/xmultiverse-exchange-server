/*
 * @(#)AppsConst.java 2015-4-14 下午2:02:23
 * Copyright 2015 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.consts;

import com.anyex.apps.utils.PropertiesUtils;

/**
 * <p>File：AppsConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-14 下午2:02:23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class AppsConst
{
    public static final PropertiesUtils properties = new PropertiesUtils("apps.properties");

    private AppsConst()
    {// 防止实例化
    }

    /**
     * 阿里云的访问·KEY
     */
    public static final String   ALIYUN_ACCESS_KEY             = properties.getProperty("aliyun.access.key.prop");

    /**
     * 阿里云的访问·秘钥
     */
    public static final String   ALIYUN_ACCESS_SECRET          = properties.getProperty("aliyun.access.secret.prop");

    /**
     * OSS访问地址
     */
    public static final String   ALIYUN_OSS_ENDPOINT           = properties.getProperty("aliyun.oss.endpoint.prop");

    /**
     * AWS访问密钥ID
     */
    public static final String   AWS_ACCESS_KEY_ID             = properties.getProperty("aws.access.key.id.prop");

    /**
     * AWS私有访问密钥
     */
    public static final String   AWS_SECRET_ACCESS_KEY         = properties.getProperty("aws.secret.access.prop");

    /**
     * 邮件发送类型
     */
    public static final String   EMAIL_SENDER_PROVIDER         = properties.getProperty("exchange.email.provider");

    /**
     * 正式空间
     */
    public static final String   BUCKET_BITMS_NAME             = properties.getProperty("exchange.bucket.release.name");

    public static final String   BUCKET_BITMS_URL              = properties.getProperty("exchange.bucket.release.url");

    /**
     * 临时空间
     */
    public static final String   BUCKET_TEMP_NAME              = properties.getProperty("exchange.bucket.temp.name");

    public static final String   BUCKET_TEMP_URL               = properties.getProperty("exchange.bucket.temp.url");

    /**
     * 默认密码
     */
    public static final String   DEFAULT_USER_PASSWORD         = properties.getProperty("exchange.default.pwd.prop");

    /**
     * HOSTNAME
     */
    public static final String   HOST_URL                      = properties.getProperty("exchange.server.url.prop");

    /**
     * 邮件发送logo地址
     */
    public static final String   HOST_EMAIL_LOGO_URL           = new StringBuilder(properties.getProperty("exchange.server.url.prop")).append("/images/bitms/bitms-email.png")
            .toString();

    /**
     * 运行环境
     */
    public static final String   RUNNING_ENVIRONMONT           = properties.getProperty("exchange.running.env.prop");

    /**
     * 子项目名称
     */
    public static final String   BITMS_PROJECT_NAME            = properties.getProperty("exchange.project.name.prop");

    /**
     * IP limit
     */
    public static final Integer  DEFAULT_REQUEST_LIMIT         = properties.getInteger("exchange.request.limits", 5);

    /**
     * 请求过期时间
     */
    public static final Integer  DEFAULT_REQUEST_TIMEOUT       = properties.getInteger("bitms.request.timeout", 10000);

    /**
     * XSS转义开关
     */
    public static final String   INTERCEPT_XSS_SWITCH          = properties.getProperty("exchange.xss.intercept.switch");

    /**
     * 短信提醒开关
     */
    public static final String   REMIND_PHONE_SWITCH           = properties.getProperty("exchange.remind.phone");

    /**
     * 邮件提醒开关
     */
    public static final String   REMIND_EMAIL_SWITCH           = properties.getProperty("exchange.remind.email");

    /**
     * sofa消费者需要消费的RPC服务地址与端口
     */
    public static final String   SOFA_RPC_BOLT_URL             = properties.getProperty("sofa.rpc.bolt.url");

    /**
     * 回测平台api root url
     */
    public static final String   BACKTEST_ROOT_URL            = properties.getProperty("backtest.root.base.url");

}
