package com.anyex.apps.shiro.model;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *  登录参数对象
 * <p>File： UserToken.java </p>
 * <p>Title:  UserToken </p>
 * <p>Description: UserToken </p>
 * <p>Copyright: Copyright (c) 2017/8/2 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class UserToken extends UsernamePasswordToken
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5758802251381953812L;
    
    private Long              id;
    
    // GA码
    private String            gaCode;
    
    public UserToken()
    {
        super();
    }
    
    public UserToken(String username, char[] password)
    {
        super(username, password);
    }
    
    public UserToken(String username, String password)
    {
        super(username, password);
    }
    
    public UserToken(String username, char[] password, String host)
    {
        super(username, password, host);
    }
    
    public UserToken(String username, String password, String host)
    {
        super(username, password, host);
    }
    
    public UserToken(String username, char[] password, boolean rememberMe)
    {
        super(username, password, rememberMe);
    }
    
    public UserToken(String username, String password, boolean rememberMe)
    {
        super(username, password, rememberMe);
    }
    
    public UserToken(String username, char[] password, boolean rememberMe, String host)
    {
        super(username, password, rememberMe, host);
    }
    
    public UserToken(String username, String password, boolean rememberMe, String host)
    {
        super(username, password, rememberMe, host);
    }
}
