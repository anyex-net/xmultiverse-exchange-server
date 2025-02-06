//package com.anyex.apps.shiro.model;
//
//import com.anyex.apps.account.enums.LoginEnums;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.apache.shiro.authc.UsernamePasswordToken;
//
//import javax.validation.constraints.NotNull;
//
///**
// *  登录设备参数对象
// * <p>File： AccountDeviceToken.java </p>
// * <p>Title:  AccountDeviceToken </p>
// * <p>Description: AccountDeviceToken </p>
// * <p>Copyright: Copyright (c) 2017/8/2 </p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class AccountDeviceToken extends UsernamePasswordToken
//{
//    /**
//     * serialVersionUID
//     */
//    private static final long serialVersionUID = 5758802251381953812L;
//
//    /**
//     * 登录类型
//     */
//    @NotNull(message = "登录类型不可为空")
//    private String loginType = LoginEnums.SMS.getCode();
//
//    /**
//     * 授权码
//     */
//    @NotNull(message = "授权码不可为空")
//    @ApiModelProperty(value = "授权码", required = true)
//    private String authCode;
//
//    ///////////////////////////////////
//    /**设备类型(ios、android、client)*/
//    @NotNull(message = "设备类型(ios、android、client)不可为空")
//    @ApiModelProperty(value = "设备类型(ios、android、client)", required = true)
//    private String deviceType;
//
//    /**设备名字*/
//    @NotNull(message = "设备名字不可为空")
//    @ApiModelProperty(value = "设备名字", required = true)
//    private String deviceName;
//
//    /**设备编码*/
//    @NotNull(message = "设备编码不可为空")
//    @ApiModelProperty(value = "设备编码", required = true)
//    private String deviceNumber;
//
//    /**版本号*/
//    @NotNull(message = "版本号不可为空")
//    @ApiModelProperty(value = "版本号", required = true)
//    private String appVersion;
//
//    /**build版本号*/
//    @NotNull(message = "build版本号不可为空")
//    @ApiModelProperty(value = "build版本号", required = true)
//    private String buildVersion;
//    ///////////////////////////////////
//
//    public AccountDeviceToken()
//    {
//        super();
//    }
//
//    public AccountDeviceToken(String username, char[] password)
//    {
//        super(username, password);
//    }
//
//    public AccountDeviceToken(String username, String password)
//    {
//        super(username, password);
//    }
//
//    public AccountDeviceToken(String username, char[] password, String host)
//    {
//        super(username, password, host);
//    }
//
//    public AccountDeviceToken(String username, String password, String host)
//    {
//        super(username, password, host);
//    }
//
//    public AccountDeviceToken(String username, char[] password, boolean rememberMe)
//    {
//        super(username, password, rememberMe);
//    }
//
//    public AccountDeviceToken(String username, String password, boolean rememberMe)
//    {
//        super(username, password, rememberMe);
//    }
//
//    public AccountDeviceToken(String username, char[] password, boolean rememberMe, String host)
//    {
//        super(username, password, rememberMe, host);
//    }
//
//    public AccountDeviceToken(String username, String password, boolean rememberMe, String host)
//    {
//        super(username, password, rememberMe, host);
//    }
//}
