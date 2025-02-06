package com.anyex.apps.utils;

import com.anyex.apps.consts.CharsetConst;
import com.anyex.apps.exception.BusinessException;
import com.google.common.collect.Maps;
import com.anyex.apps.bean.ClientParameter;
import com.anyex.apps.bean.EnumDescribable;
import com.anyex.apps.enums.CommonEnums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>File：ParameterUtils.java</p>
 * <p>Title: API开发平台参数解析处理工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-25 下午4:25:35</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class ParameterUtils
{
    private static final String MESSAGE_FLAG = "0XFF";
    
    private static final String MESSAGE_ELAG = "0X00";
    
    private static final String MESSAGE_SPEC = ",";
    
    private static final String MESSAGE_LINK = ":";
    
    private static final String FUZZY_CHAR   = "%";
    
    // 私有构造器
    private ParameterUtils()
    {
        super();
    }
    
    /**
     * API访问参数校验
     *
     * @param parameter ClientParameter
     * @return EnumDescribable 参数检查结果，相关描述参靠ClientConst
     */
    public static EnumDescribable checkParameter(ClientParameter parameter)
    {
        EnumDescribable code;
        if (null != parameter)
        {
            Integer dataLen = parameter.getDataLen();
            if (null != dataLen)
            {
                if (validateClientDataLength(parameter.getDataLen(), parameter.getData()))
                {
                    String userKey = StringUtils.trimToEmpty(parameter.getUserKey());
                    String userDes = StringUtils.trimToEmpty(parameter.getUserDes());
                    if (validateClientData(userDes, userKey, dataLen))
                    {
                        code = CommonEnums.SUCCESS;
                    }
                    else
                    {
                        code = CommonEnums.ERROR_DES_CHECK_FAILED;
                    }
                }
                else
                {
                    code = CommonEnums.ERROR_DATA_LENGTH_FAILED;
                }
            }
            else
            {
                code = CommonEnums.ERROR_DATA_LENGTH_FAILED;
            }
        }
        else
        {
            code = CommonEnums.ERROR_PARAMS_VALID;
        }
        return code;
    }
    
    /**
     * 校验数据长度
     *
     * @param dataLen 客户端请求中的长度参数
     * @param data    客户端请求中的实际数据
     * @return
     * @author Playguy
     */
    public static boolean validateClientDataLength(Integer dataLen, String data)
    {
        int len = ValidateUtils.length(data);
        return len == dataLen;
    }
    
    /**
     * 校验异或校验码
     *
     * @param userDes
     * @param userKey
     * @param dataLen
     * @return
     * @author Playguy
     */
    public static boolean validateClientData(String userDes, String userKey, Integer dataLen)
    {
        String encrypt = getUserDes(userKey, dataLen);
        return StringUtils.equals(userDes, encrypt);
    }
    
    /**
     * 从ClientParameter对象获取参数键值对，返回Map<String, String>
     *
     * @param parameter ClientParameter
     * @return Map<String, String> Map<String, String>
     */
    public static Map<String, String> getMapFromParameter(ClientParameter parameter)
    {
        String data = null;
        if (null != parameter)
        {
            data = parameter.getData();
        }
        return getMapFromData(data);
    }
    
    /**
     * 从ClientParameter对象获取参数键值对，返回对象
     *
     * @param parameter ClientParameter
     * @return Object
     */
    public static <T> T getObjectFromParameter(ClientParameter parameter, Class<T> clazz)
    {
        String data = null;
        if (null != parameter)
        {
            data = parameter.getData();
        }
        Map<String, String> values = getMapFromData(data);
        T retObj = null;
        try
        {
            retObj = clazz.newInstance();
            BeanUtils.populate(retObj, values);
        }
        catch (Exception e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return retObj;
    }
    
    /**
     * 接收并解析消息体：根据消息体内容，取得Map<String,String>键值对
     *
     * @param data 消息体内容
     * @return Map<String, String> Map<String, String>
     */
    public static Map<String, String> getMapFromData(String data)
    {
        Map<String, String> map = Maps.newHashMap();
        if (StringUtils.isNotBlank(data))
        {
            String[] strings = StringUtils.split(data, MESSAGE_SPEC);
            if (null != strings && strings.length > 0)
            {
                for (int i = 0; i < strings.length; i++)
                {
                    String[] keyValue = StringUtils.split(strings[i], MESSAGE_LINK);
                    int len = keyValue.length;
                    if (null != keyValue && len > 0)
                    {
                        if (len == 2)
                        {
                            String valueString = StringUtils.trimToEmpty(keyValue[1]);
                            valueString = StringUtils.replace(valueString, MESSAGE_FLAG, MESSAGE_SPEC);
                            valueString = StringUtils.replace(valueString, MESSAGE_ELAG, MESSAGE_LINK);
                            map.put(StringUtils.trimToEmpty(keyValue[0]), valueString);
                        }
                        else if (len == 1)
                        {
                            map.put(StringUtils.trimToEmpty(keyValue[0]), "");
                        }
                    }
                }
            }
        }
        return map;
    }
    
    /**
     * 发送参数：将原始的map参数键值对转码并组合为data消息体
     *
     * @param map 参数键值对
     * @return String 消息体内容
     */
    public static String getDataFromMap(Map<String, String> map)
    {
        String result = null;
        if (null != map && !map.isEmpty())
        {
            StringBuffer stringBuffer = new StringBuffer();
            for (Entry<String, String> entry : map.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                value = StringUtils.replace(value, MESSAGE_SPEC, MESSAGE_FLAG);
                value = StringUtils.replace(value, MESSAGE_LINK, MESSAGE_ELAG);
                stringBuffer.append(key).append(MESSAGE_LINK);
                stringBuffer.append(value).append(MESSAGE_SPEC);
            }
            String tempString = stringBuffer.toString();
            result = StringUtils.substring(tempString, 0, tempString.length() - 1);
            // result = Jsoup.clean(result, Whitelist.relaxed());
        }
        return StringUtils.trimToEmpty(result);
    }
    
    /**
     * 根据协议格式计算异或校验码
     *
     * @param userKey 异或校验码
     * @param dataLen data参数值长度
     * @return String 异或校验码
     */
    public static String getUserDes(String userKey, Integer dataLen)
    {
        String result = null;
        if (null != dataLen && StringUtils.isNotBlank(userKey))
        {
            StringBuffer stringBuffer = new StringBuffer(userKey);
            stringBuffer.append(dataLen);
            result = EncodeUtils.getXorString(stringBuffer.toString(), CharsetConst.CHARSET_UT);
        }
        return result;
    }
    
    /**
     * 用于包装数据库模糊查询时的字符串参数， 比如传入 "123", 会返回 "%123%"
     *
     * @param param 模糊查询的字符串
     * @return
     * @author Playguy
     */
    public static String getFuzzyQueryString(String param)
    {
        return new StringBuilder(FUZZY_CHAR).append(param).append(FUZZY_CHAR).toString();
    }
    
    /**
     * 根据参数键值对组合data消息体
     *
     * @param map 参数键值对
     * @return String 消息体内容
     */
    public static String getData(Map<String, Object> map)
    {
        String result = null;
        if (null != map && !map.isEmpty())
        {
            StringBuffer stringBuffer = new StringBuffer();
            for (Entry<String, Object> entry : map.entrySet())
            {
                String key = entry.getKey();
                String value = (String) entry.getValue();
                value = StringUtils.replace(value, MESSAGE_SPEC, MESSAGE_FLAG);
                value = StringUtils.replace(value, MESSAGE_LINK, MESSAGE_ELAG);
                stringBuffer.append(key).append(MESSAGE_LINK);
                stringBuffer.append(value).append(MESSAGE_SPEC);
            }
            String tempString = stringBuffer.toString();
            result = StringUtils.substring(tempString, 0, tempString.length() - 1);
        }
        return result;
    }
    
    /**
     * 判断字符串是否是UUID格式
     *
     * @param param 要检测的字符串
     * @return 如果是UUID格式，返回true，否则返回false
     * @author Playguy
     */
    public static boolean isUUID(String param)
    {
        return StringUtils.isNotBlank(param) && param.length() == 32;
    }
    
    public static String getStringValue(Map<String, String> data, String fieldName, Boolean isRequire) throws BusinessException
    {
        String value = MapUtils.getString(data, fieldName);
        if (StringUtils.isBlank(value))
        {
            if (isRequire)
            { throw new BusinessException(CommonEnums.FAIL.code, fieldName + "不能为空"); }
            return null;
        }
        else
        {
            return value.trim();
        }
    }
    
    public static Integer getIntegerValue(Map<String, String> data, String fieldName, Boolean isRequire) throws BusinessException
    {
        Integer value = MapUtils.getInteger(data, fieldName);
        if (null == value)
        {
            if (isRequire)
            { throw new BusinessException(CommonEnums.FAIL.code, fieldName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static Double getDoubleValue(Map<String, String> data, String fieldName, Boolean isRequire) throws BusinessException
    {
        Double value = MapUtils.getDouble(data, fieldName);
        if (null == value)
        {
            if (isRequire)
            { throw new BusinessException(CommonEnums.FAIL.code, fieldName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static Long getLongValue(Map<String, String> data, String fieldName, Boolean isRequire) throws BusinessException
    {
        Long value = MapUtils.getLong(data, fieldName);
        if (null == value)
        {
            if (isRequire)
            { throw new BusinessException(CommonEnums.FAIL.code, fieldName + "不能为空"); }
            return null;
        }
        else
        {
            return value;
        }
    }
    
    public static BigDecimal getBigDecimalValue(Map<String, String> data, String fieldName, Boolean isRequire) throws BusinessException
    {
        String value = getStringValue(data, fieldName, isRequire);
        if (null == value)
        {
            if (isRequire)
            { throw new BusinessException(CommonEnums.FAIL.code, fieldName + "不能为空"); }
            return null;
        }
        else
        {
            return new BigDecimal(value);
        }
    }
    
//    public static void main(String[] args)
//    {
//        System.out.println(SerialnoUtils.buildUUID());
//        String userKey = "FE3141781F1441B2B912BF47934BF334";
//        String data = "refrenceId:0AC15143E6F548038292686BDA878B49";
//        int dataLen = ValidateUtils.length(data);
//        String userDes = getUserDes(userKey, dataLen);
//        System.out.println("userKey=" + userKey);
//        System.out.println("dataLen=" + dataLen);
//        System.out.println("userDes=" + userDes);
//        System.out.println("data=" + data);
//    }
}
