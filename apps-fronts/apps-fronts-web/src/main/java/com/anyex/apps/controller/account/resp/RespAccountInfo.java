package com.anyex.apps.controller.account.resp;//package com.anyex.apps.account.model.resp;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 账户个人信息应答对象
// * <p>File：RespAccountInfo.java</p>
// * <p>Title: RespAccountInfo</p>
// * <p>Description: RespAccountInfo</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class RespAccountInfo implements Serializable
//{
//    /**ID*/
//    @NotNull(message = "ID不可为空")
//    @JsonSerialize(using = ToStringSerializer.class)
//    @ApiModelProperty(value = "ID", required = true)
//    private Long id;
//
//    /**UID*/
//    @NotNull(message = "UID不可为空")
//    @ApiModelProperty(value = "UID", required = true)
//    private Long uid;
//
//    /**账户名*/
//    @NotNull(message = "账户名不可为空")
//    @ApiModelProperty(value = "账户名", required = true)
//    private String accountName;
//
//    /**
//     * 电子邮箱
//     */
//    @NotNull(message = "电子邮箱不可为空")
//    @ApiModelProperty(value = "电子邮箱", required = true)
//    private String email;
//
//    /**
//     * 国家地区
//     */
//    @NotNull(message = "国家地区不可为空")
//    @ApiModelProperty(value = "国家地区", required = true)
//    private String country;
//
//    /**
//     * 手机号码
//     */
//    @NotNull(message = "手机号码不可为空")
//    @ApiModelProperty(value = "手机号码", required = true)
//    private String mobNo;
//
//    /**
//     * Google验证器私钥
//     */
//    @NotNull(message = "Google验证器私钥不可为空")
//    @ApiModelProperty(value = "Google验证器私钥", required = true)
//    private String gaauthKey;
//
//    /**
//     * 资金密码
//     */
//    @NotNull(message = "资金密码不可为空")
//    @ApiModelProperty(value = "资金密码", required = true)
//    private String cashPwd;
//
//    /**
//     * 安全验证策略
//     */
//    @NotNull(message = "安全验证策略不可为空")
//    @ApiModelProperty(value = "安全验证策略", required = true)
//    private Integer securityPolicy;
//
//    /**
//     * 交易验证策略
//     */
//    @NotNull(message = "交易验证策略不可为空")
//    @ApiModelProperty(value = "交易验证策略", required = true)
//    private Integer tradePolicy;
//
//    /**
//     * 邀请码
//     */
//    @NotNull(message = "邀请码不可为空")
//    @ApiModelProperty(value = "邀请码", required = true)
//    private String inviteCode;
//
//    /**
//     * token
//     */
//    @NotNull(message = "token不可为空")
//    @ApiModelProperty(value = "token", required = true)
//    private String token;
//}
