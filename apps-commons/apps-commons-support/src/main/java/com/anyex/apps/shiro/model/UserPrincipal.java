package com.anyex.apps.shiro.model;

import com.anyex.apps.system.entity.SysRoleInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>File：UserPrincipal.java </p>
 * <p>Title: UserPrincipal </p>
 * <p>Description: UserPrincipal </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 20:41</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
@Data
public class UserPrincipal implements Serializable
{
    private static final long serialVersionUID = 191434150385861991L;
    
    /**主键编号*/
    private Long              id;

    /**用户编号*/
    private Long              unid;

    /**国家地区*/
    private String            country;
    
    /**语言标识*/
    private String            lang;
    
    /**帐号*/
    private String            userName;
    
    /**昵称*/
    private String            nickName;
    
    /**头像*/
    private String            userLogo;
    
    /**手机号码*/
    private String            userMobile;
    
    /**电子邮箱*/
    private String            userMail;

    /**google验证器私钥*/
    private String            gaAuthKey;

    /**认证状态*/
    private Short             certification;
    
    /**角色和权限**/
    private List<SysRoleInfo> roles;

    //IM
    private String            imToken;
    
    /**
     * DEFAULT CONSTRUCTOR
     * @param id
     */
    public UserPrincipal(Long id)
    {
        this.id = id;
    }
    
    public UserPrincipal(Long id, String userName, String nickName, List<SysRoleInfo> roles)
    {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.roles = roles;
    }
    
    public UserPrincipal(Long id, String userName, String nickName, String userLogo, List<SysRoleInfo> roles)
    {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.userLogo = userLogo;
        this.roles = roles;
    }

    public UserPrincipal(Long id, String userName, String nickName, String userMobile, String userMail)
    {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.userMobile = userMobile;
        this.userMail = userMail;
    }

    public UserPrincipal(Long id, String userName, String nickName, String userMobile, String userMail, String imToken)
    {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.userMobile = userMobile;
        this.userMail = userMail;
        this.imToken = imToken;
    }

    public UserPrincipal(Long id, Long unid, String userName, String nickName, String userMobile, String userMail, String lang, String country, String gaAuthKey)
    {
        this.id = id;
        this.unid = unid;
        this.userName = userName;
        this.nickName = nickName;
        this.userMobile = userMobile;
        this.userMail = userMail;
        this.lang = lang;
        this.country = country;
        this.gaAuthKey = gaAuthKey;
    }
}
