package com.anyex.apps.shiro.model;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 * <p>File：JwtToken.java</p>
 * <p>Title: JwtToken</p>
 * <p>Description: JwtToken</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class JwtToken implements AuthenticationToken
{
    private String token;
    
    public JwtToken(String token)
    {
        this.token = token;
    }
    
    @Override
    public Object getPrincipal()
    {
        return token;
    }
    
    @Override
    public Object getCredentials()
    {
        return token;
    }
}
