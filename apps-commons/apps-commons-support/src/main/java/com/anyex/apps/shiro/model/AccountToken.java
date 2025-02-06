package com.anyex.apps.shiro.model;

import lombok.Data;
import lombok.ToString;
import org.apache.shiro.authc.UsernamePasswordToken;

import javax.validation.constraints.NotNull;

/**
 *  登录参数对象
 * <p>File： AccountToken.java </p>
 * <p>Title:  AccountToken </p>
 * <p>Description: AccountToken </p>
 * <p>Copyright: Copyright (c) 2017/8/2 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
public class AccountToken extends UsernamePasswordToken
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5758802251381953812L;

    /**
     * 登录类型
     */
    @NotNull(message = "登录类型不可为空")
    private String            loginType;

    /**
     * 授权码
     */
    @NotNull(message = "授权码不可为空")
    private String            authCode;
    
    public AccountToken()
    {
        super();
    }
    
    public AccountToken(String username, char[] password)
    {
        super(username, password);
    }
    
    public AccountToken(String username, String password)
    {
        super(username, password);
    }
    
    public AccountToken(String username, char[] password, String host)
    {
        super(username, password, host);
    }
    
    public AccountToken(String username, String password, String host)
    {
        super(username, password, host);
    }
    
    public AccountToken(String username, char[] password, boolean rememberMe)
    {
        super(username, password, rememberMe);
    }
    
    public AccountToken(String username, String password, boolean rememberMe)
    {
        super(username, password, rememberMe);
    }
    
    public AccountToken(String username, char[] password, boolean rememberMe, String host)
    {
        super(username, password, rememberMe, host);
    }
    
    public AccountToken(String username, String password, boolean rememberMe, String host)
    {
        super(username, password, rememberMe, host);
    }
}
