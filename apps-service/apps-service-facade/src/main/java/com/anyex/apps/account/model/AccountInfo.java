//package com.anyex.fhsc.account.model;
//
//import java.io.Serializable;
//
//import javax.validation.constraints.NotNull;
//
//import com.anyex.fhsc.account.entity.Statistics;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
///**
// * AccountInfo
// * <p>File：AccountInfo.java</p>
// * <p>Title: AccountInfo</p>
// * <p>Description: AccountInfo</p>
// * <p>Copyright: Copyright (c) 2019/11/4</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class AccountInfo implements Serializable
//{
//    @JsonSerialize(using = ToStringSerializer.class)
//    private Long       id;
//
//    @ApiModelProperty(value = "账户昵称")
//    private String     nickName;
//
//    @ApiModelProperty(value = "账户头像")
//    private String     accountLogo;
//
//    /**哔哔号*/
//    @ApiModelProperty(value = "哔哔号")
//    private boolean    bibiH;
//
//    /*** 邮箱*/
//    @ApiModelProperty(value = "邮箱")
//    private String     email;
//
//    /*** 手机号*/
//    @NotNull(message = "手机号不可为空")
//    @ApiModelProperty(value = "手机号")
//    private String     phone;
//
//    /*** 用户简介*/
//    @ApiModelProperty(value = "用户简介", required = true)
//    private String     summary;
//
//    /*** 用户性别*/
//    @ApiModelProperty(value = "用户性别", required = true)
//    private String     sex;
//
//    @ApiModelProperty(value = "统计信息")
//    private Statistics statistics;
//
//    /*** 微信绑定状态(>0为已绑定)*/
//    private String     wxStatus;
//}
