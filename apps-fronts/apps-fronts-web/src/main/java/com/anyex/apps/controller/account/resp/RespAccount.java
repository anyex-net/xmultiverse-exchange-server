package com.anyex.apps.controller.account.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 账户信息应答对象
 * <p>File：RespAccount.java</p>
 * <p>Title: RespAccount</p>
 * <p>Description: RespAccount</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class RespAccount implements Serializable
{
    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private Long id;

    /**账户编号*/
    @ApiModelProperty(value = "账户编号")
    private Long unid;

    @ApiModelProperty(value = "IM userId")
    private java.lang.String userId;

    /**国家默认86(巴基斯坦92)*/
    @ApiModelProperty(value = "国家默认86(巴基斯坦92)")
    private String country;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**账户昵称*/
    @ApiModelProperty(value = "账户昵称")
    private String accountName;

    /**账户姓名*/
    @ApiModelProperty(value = "账户姓名")
    private String realName;

    /**CNIC*/
    @ApiModelProperty(value = "CNIC")
    private String cnic;

    /**头像URL*/
    @ApiModelProperty(value = "头像URL")
    private String headUrl;

    /**邮箱*/
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**生日*/
    @ApiModelProperty(value = "生日")
    private String birth;

    /**性别(0男、1女)*/
    @ApiModelProperty(value = "性别(0男、1女)")
    private Boolean gender;

    /**最新位置经度*/
    @ApiModelProperty(value = "最新位置经度")
    private String lng;

    /**最新位置维度*/
    @ApiModelProperty(value = "最新位置维度")
    private String lat;

    /**邀请码*/
    @ApiModelProperty(value = "邀请码")
    private String invitationCode;

    /**推荐码*/
    @ApiModelProperty(value = "推荐码")
    private String referralCode;

    /**来源*/
    @ApiModelProperty(value = "来源")
    private String source;

    /**状态(0:正常、1:冻结、2:注销)*/
    @ApiModelProperty(value = "状态(0:正常、1:冻结、2:注销)")
    private Integer status;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

    /**余额*/
    @ApiModelProperty(value = "余额")
    private java.math.BigDecimal balance;

    /**冻结(不可用)*/
    @ApiModelProperty(value = "冻结(不可用)")
    private java.math.BigDecimal frozenBal;
}
