/*
 * @(#)SerialNumberUtils.java 2014-1-8 下午1:07:00
 * Copyright 2014 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.utils;

import com.anyex.apps.bean.IdWorker;
import com.anyex.apps.bean.SpringContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 * <p>File：SerialNumberUtils.java</p>
 * <p>Title: 系统唯一编号生成工具类</p>
 * <p>Description:主要功能为主键、订单、支付、退款、发货、退货等编号的生成 </p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:07:00</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class SerialnoUtils
{
    static String   TABLE = "0123456789";

    static IdWorker idworker = SpringContext.getBean(IdWorker.class);

    // 私有构造器，防止类的实例化
    private SerialnoUtils()
    {
        super();
    }

    /**
     * 创建数据库主键
     *
     * @return String 数据库主键
     */
    public static Long buildPrimaryKey()
    {
        if (null == idworker)
        {
            idworker = new IdWorker(1, 1);
        }
        return idworker.nextId();
    }

    /**
     * 创建唯一编码
     *
     * @return String 数据库主键
     */
    public static String buildUUID()
    {
        String uuid = UUID.randomUUID().toString();
        return StringUtils.replace(uuid, "-", "").toLowerCase();
    }

    /**
     * 创建指定长度的随机数
     *
     * @param length
     * @return
     */
    public static String randomNum(int length)
    {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i)
        {
            int number = random.nextInt(TABLE.length());
            sb.append(TABLE.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 订单号 规则: yyyyMMddHHmm(12位) + 唯一key(18位)
     *
     * @return
     */
    public static String getOrderNum() {
//        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//        String localDate = LocalDateTime.now().format(ofPattern);
//        return localDate + buildPrimaryKey();
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String localDate = LocalDateTime.now().format(ofPattern);
        return localDate + randomNum(8);
    }
}
