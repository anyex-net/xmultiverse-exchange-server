package com.anyex.apps.exception;

import com.anyex.apps.bean.EnumDescribable;
import com.anyex.apps.bean.ErrorInfo;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationException;

/**
 * 帐户安全策略异常
 *
 * @author Playguy
 */
@Data
public class AccountPolicyException extends AuthenticationException
{
    private static final long serialVersionUID = -8544606640817367976L;
    
    /**
     * 错误代码
     */
    private Integer           code;
    
    /**
     * 消息对象
     */
    private Object            object;
    
    /**
     * 错误描述
     */
    private EnumDescribable   error;
    
    /**
     * 目前业务异常非常多，业务异常代码未进行整理，
     * 考虑到后期统一对业务异常代码进行规范编号的问题，
     * 目前开放此接口解决困扰程序员找不到特定的错误代码的问题
     *
     * @param message
     */
    public AccountPolicyException(String message)
    {
        super(message);
        this.code = 9999;
        this.error = new ErrorInfo(code, message);
    }
    
    public AccountPolicyException(Integer code, String message)
    {
        super(message);
        this.code = code;
        this.error = new ErrorInfo(code, message);
    }
    
    public AccountPolicyException(Integer code, String message, Object object)
    {
        this(code, message);
        this.object = object;
    }
    
    /**
     * @author Playguy
     */
    public AccountPolicyException(EnumDescribable describable)
    {
        super(new StringBuilder("error code: ").append(describable.getCode()).append(", description: ").append(describable.getMessage()).toString());
        this.error = describable;
    }
    
    /**
     * @param describable
     * @param object
     * @author Playguy
     */
    public AccountPolicyException(EnumDescribable describable, Object object)
    {
        super(new StringBuilder("error code: ").append(describable.getCode()).append(", description: ").append(describable.getMessage()).toString());
        this.error = describable;
        this.object = object;
    }
}
