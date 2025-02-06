package com.anyex.netease.builder;

import com.anyex.apps.utils.EncryptUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.security.MessageDigest;

public class CheckSumBuilder {

    // 计算并获取CheckSum
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    // 计算并获取md5值
    public static String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }


    // checkHeader
    public static Boolean checkHeaderAndBody(String md5,String curTime,String checkSum,String jsonstr) {
        String appSercret = EncryptUtils.desDecrypt(NeteaseConfig.getAppSecret());
        /**
         *  String AppSecret = "90ud57s6****";
         *     String MD5 = "9894907e4ad9de467809127750******";
         *     String CurTime = "1440570500855";  ////当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的毫秒数(String)
         *     String CheckSum = CheckSumBuilder.getCheckSum(AppSecret, MD5, CurTime); //参考 接口概述 -> API checksum校验 部分
         */
        String checkSumServer = getCheckSum(appSercret, md5, curTime);
        if(!StringUtils.equalsIgnoreCase(checkSum,checkSumServer))
        {
            System.out.println("checkSum校验失败");
            return false;
        }
        /**
         *   String requestBody = "{}";
         *     String MD5 = CheckSumBuilder.getMD5(requestBody); //参考 接口概述 -> API checksum校验 部分
         */
        String md5server = CheckSumBuilder.getMD5(jsonstr);
        if(!StringUtils.equalsIgnoreCase(md5,md5server))
        {
            System.out.println("body校验失败");
            return false;
        }
        return true;
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
