package com.anyex.apps.shiro.model;

import com.anyex.apps.user.model.PolicyModel;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *  登陆参数对象
 * <p>File： AccountToken.java </p>
 * <p>Title:  AccountToken </p>
 * <p>Description: AccountToken </p>
 * <p>Copyright: Copyright (c) 2017/8/2 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class UserToken extends UsernamePasswordToken
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5758802251381953812L;

    // ID
    private Long              id;

    // 策略对象
    private PolicyModel       policy;

    // 策略用户
    private Integer           level;

    // 手机号
    private String            mobNo;

    // GA码
    private String            gaCode;

    // 国家代码
    private String            country;

    // GA是否绑定
    private boolean           ga;

    // 手机是否绑定
    private boolean           phone;

    // 唯一二维码串
    private String            qrCode;

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
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public PolicyModel getPolicy()
    {
        return policy;
    }
    
    public void setPolicy(PolicyModel policy)
    {
        this.policy = policy;
    }
    
    public Integer getLevel()
    {
        return level;
    }
    
    public void setLevel(Integer level)
    {
        this.level = level;
    }
    
    public String getMobNo()
    {
        return mobNo;
    }
    
    public void setMobNo(String mobNo)
    {
        this.mobNo = mobNo;
    }
    
    public String getGaCode()
    {
        return gaCode;
    }
    
    public void setGaCode(String gaCode)
    {
        this.gaCode = gaCode;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public boolean isGa()
    {
        return ga;
    }
    
    public void setGa(boolean ga)
    {
        this.ga = ga;
    }
    
    public boolean isPhone()
    {
        return phone;
    }
    
    public void setPhone(boolean phone)
    {
        this.phone = phone;
    }

    public String getQrCode()
    {
        return qrCode;
    }

    public void setQrCode(String qrCode)
    {
        this.qrCode = qrCode;
    }
}
