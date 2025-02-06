package com.anyex.apps.utils;

import java.math.BigInteger;

/**
 * 进制转换工具类
 */
public class ConversionUtils
{
    /**
     * 初始化 62 进制数据，索引位置代表字符的数值，比如 A代表10，z代表61等
     */
    private static String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    private static int    scale = 62;
    
    /**
     * 将十进制转二进制
     *
     * @param number
     * @return
     */
    public static String toBinaryString(Long number)
    {
        return Long.toBinaryString(number);
    }
    
    /**
     * 将十进制转八进制
     *
     * @param number
     * @return
     */
    public static String toOctalString(Long number)
    {
        return Long.toOctalString(number);
    }
    
    /**
     * 将十进制转十六进制
     *
     * @param number
     * @return
     */
    public static String toHexString(Long number)
    {
        return Long.toHexString(number);
    }
    
    /**
     * 将十进制转十六进制
     *
     * @param number
     * @return
     */
    public static String toHexString(BigInteger number)
    {
        return number.toString(16);
    }
    
    /**
     * 将二进制转十进制
     *
     * @param string
     * @return
     */
    public static Long fromBinaryString(String string)
    {
        return Long.parseLong(string, 2);
    }
    
    /**
     * 将8进制转十进制
     *
     * @param string
     * @return
     */
    public static Long fromOctalString(String string)
    {
        return Long.parseLong(string, 8);
    }
    
    /**
     * 将十六进制转十进制
     *
     * @param string
     * @return
     */
    public static Long fromHexString(String string)
    {
        return Long.parseLong(string, 16);
    }
    
    /**
     * 字符串转化成为16进制字符串
     *
     * @param s
     * @return
     */
    public static String toHexString(String s)
    {
        String str = "";
        for (int i = 0; i < s.length(); i++)
        {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    
    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStrToString(String s)
    {
        if (s == null || s.equals(""))
        { return null; }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++)
        {
            try
            {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            s = new String(baKeyword, "UTF-8");
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        return s;
    }
    
    /**
     * 将数字转为62进制
     *
     * @param num    Long 型数字
     * @param length 转换后的字符串长度，不足则左侧补0
     * @return 62进制字符串
     */
    public static String tenRadixToSixTyTwoRadix(long num, int length)
    {
        StringBuilder sb = new StringBuilder();
        int remainder = 0;
        while (num > scale - 1)
        {
            /**
             * 对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转（reverse）字符串
             */
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        String value = sb.reverse().toString();
        return length > 0 ? StringUtils.leftPad(value, length, '0') : value;
    }
    
    /**
     * 62进制字符串转为数字
     *
     * @param str 编码后的62进制字符串
     * @return 解码后的 10 进制字符串
     */
    public static long sixTyTwoRadixToTenRadix(String str)
    {
        /**
         * 将 0 开头的字符串进行替换
         */
        str = str.replace("^0*", "");
        long num = 0;
        int index = 0;
        for (int i = 0; i < str.length(); i++)
        {
            /**
             * 查找字符的索引位置
             */
            index = chars.indexOf(str.charAt(i));
            /**
             * 索引位置代表字符的数值
             */
            num += (long) (index * (Math.pow(scale, str.length() - i - 1)));
        }
        return num;
    }
    
//    public static void main(String[] args)
//    {
//        System.out.println(hexStrToString("6162736466616466617366617366617366"));
//    }
}
