package com.anyex.apps.bean;

/**
 * 错误消息对象
 * <p>File: ErrorInfo.java </p>
 * <p>Title: ErrorInfo </p>
 * <p>Description: ErrorInfo </p>
 * <p>Copyright: Copyright (c) 2019-06-10</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class ErrorInfo implements EnumDescribable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7194581434568247909L;
    
    private Integer           code;
    
    private String            message;
    
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public ErrorInfo()
    {
    }
    
    public ErrorInfo(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
