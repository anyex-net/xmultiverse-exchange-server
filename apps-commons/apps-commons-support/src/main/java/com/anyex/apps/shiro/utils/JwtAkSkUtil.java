package com.anyex.apps.shiro.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;

/**
 * JwtAkSkUtil
 * <p>File：JwtAkSkUtil.java</p>
 * <p>Title: JwtAkSkUtil</p>
 * <p>Description: JwtAkSkUtil</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class JwtAkSkUtil
{
    /**
     * 判断str字符串是否能够被regex匹配
     * 如a*b?d可以匹配aAAAbcd
     *
     * @param str   任意字符串
     * @param regex 包含*或？的匹配表达式
     * @return
     */
    public static boolean isMatch(String str, String regex) {
        return isMatch(str, regex, false);
    }

    /**
     * 判断str字符串是否能够被regex匹配
     * 如a*b?d可以匹配aAAAbcd
     *
     * @param str        任意字符串
     * @param regex      包含*或？的匹配表达式
     * @param ignoreCase 大小写敏感
     * @return
     */
    public static boolean isMatch(String str, String regex, boolean ignoreCase) {
        if (str == null || regex == null) {
            return false;
        }
        if (ignoreCase) {
            str = str.toLowerCase();
            regex = regex.toLowerCase();
        }
        return matches(str, regex.replaceAll("(^|([^\\\\]))[\\*]{2,}", "$2*"));// 去除多余*号
    }

    private static boolean matches(String str, String regex) {
        // 如果str与regex完全相等，且str不包含反斜杠，则返回true。
        if (str.equals(regex) && str.indexOf('\\') < 0) {
            return true;
        }
        int rIdx = 0, sIdx = 0;// 同时遍历源字符串与匹配表达式
        while (rIdx < regex.length() && sIdx < str.length()) {
            char c = regex.charAt(rIdx);// 以匹配表达式为主导
            switch (c) {
                case '*':// 匹配到*号进入下一层递归
                    String tempSource = str.substring(sIdx);// 去除前面已经完全匹配的前缀
                    String tempRegex = regex.substring(rIdx + 1);// 从星号后一位开始认为是新的匹配表达式
                    for (int j = 0; j <= tempSource.length(); j++) {// 此处等号不能缺，如（ABCD，*），等号能达成("", *)条件
                        if (matches(tempSource.substring(j), tempRegex)) {// 很普通的递归思路
                            return true;
                        }
                    }
                    return false;// 排除所有潜在可能性，则返回false
                case '?':
                    break;
                case '\\':// 匹配到反斜杠跳过一位，匹配下一个字符串
                    c = regex.charAt(++rIdx);
                default:
                    if (str.charAt(sIdx) != c) {
                        return false;// 普通字符的匹配
                    }
            }
            rIdx++;
            sIdx++;
        }
        // 最终str被匹配完全，而regex也被匹配完整或只剩一个*号
        return str.length() == sIdx
                && (regex.length() == rIdx || regex.length() == rIdx + 1 && regex.charAt(rIdx) == '*');
    }

    /**
     * <p>TO:加sha256加密后的值，返回16进制的字符串
     * <p>HISTORY: 2022/1/14 liuhao54 : Created.
     *
     * @param message BASE64（AK，认证摘要字符串的有效期时间戳）
     * @param secret  sk
     * @return String 认证摘要字符串
     */
    public static String hmacSHA256Sginature(String message, String secret) {
        String sginature = "";
        byte[] bytes2 = HmacUtils.hmacSha256(secret, message);
        sginature = Hex.encodeHexString(bytes2);
        return sginature;
    }

    /**
     * 生成认证字符串
     * 生成摘要字符串
     * 生成头信息
     */
    public static String getClientToken(String AK, String SK, long time) {
        /**
         * 第一步：获取系统的时间戳（自1970年1月1日0时起的毫秒数）
         */
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        //long time = System.currentTimeMillis();

        log.info(AK + ", " + SK + ", :" + time);
        /**
         * 第二步：对（ak+时间戳）求base64编码的数据
         */
        String base64String = Base64.getEncoder().encodeToString((AK + time).getBytes(StandardCharsets.UTF_8));
        /**
         * 第三步：使用sk作为密钥，对上面这个base64后的数据求sha256加密后的值，结果要求是16进制的字符串
         */
        String digestStr = hmacSHA256Sginature(base64String, SK);
        /**
         *  第四步：对(sha256加密后的值 + ":" + ak + ":" + 时间戳)求base64的信息，最终得到认证信息
         */
        return Base64.getEncoder().encodeToString((digestStr + ":" + AK + ":" + time).getBytes(StandardCharsets.UTF_8));
    }

//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String AK = "3fb17f91f041427db8326b94f2185d25";
//        String SK = "e31936f3557540bf95790c891422657d";
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 5);
//        long time1 = calendar.getTime().getTime();
//        System.out.println(time1);
//        String clientToken = JwtAkSkUtil.getClientToken(AK, SK, time1);
//        System.out.println(clientToken);
//    }
}
