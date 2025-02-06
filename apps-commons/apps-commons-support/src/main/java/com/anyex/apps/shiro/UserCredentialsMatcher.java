package com.anyex.apps.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * <p>File：UserCredentialsMatcher.java </p>
 * <p>Title: UserCredentialsMatcher </p>
 * <p>Description: UserCredentialsMatcher </p>
 * <p>Copyright: Copyright (c) 2014 08/10/2015 22:36</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
public class UserCredentialsMatcher extends SimpleCredentialsMatcher
{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authToken, AuthenticationInfo info)
    {
        return true;
    }
}
