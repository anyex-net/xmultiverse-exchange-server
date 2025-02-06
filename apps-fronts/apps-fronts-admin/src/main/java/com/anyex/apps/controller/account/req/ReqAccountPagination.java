package com.anyex.apps.controller.account.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "账户信息分页请求对象")
public class ReqAccountPagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**账户编号*/
    @ApiModelProperty(value = "账户编号")
    private java.lang.Long unid;

    @ApiModelProperty(value = "IM userId")
    private java.lang.String userId;

    /**国家默认86(巴基斯坦92)*/
    @ApiModelProperty(value = "国家默认86(巴基斯坦92)")
    private java.lang.String country;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private java.lang.String mobile;

    /**账户昵称*/
    @ApiModelProperty(value = "账户昵称")
    private java.lang.String accountName;

    /**账户姓名*/
    @ApiModelProperty(value = "账户姓名")
    private java.lang.String realName;

    /**头像URL*/
    @ApiModelProperty(value = "头像URL")
    private java.lang.String headUrl;

    /**邮箱*/
    @ApiModelProperty(value = "邮箱")
    private java.lang.String email;

    /**生日*/
    @ApiModelProperty(value = "生日")
    private java.lang.String birth;

    /**性别(0男、1女)*/
    @ApiModelProperty(value = "性别")
    private java.lang.Boolean gender;

    /**IP地址*/
    @ApiModelProperty(value = "IP地址")
    private java.lang.String ip;

    /**最新位置经度*/
    @ApiModelProperty(value = "最新位置经度")
    private java.lang.String lng;

    /**最新位置维度*/
    @ApiModelProperty(value = "最新位置维度")
    private java.lang.String lat;

    /**邀请码*/
    @ApiModelProperty(value = "邀请码")
    private java.lang.String invitationCode;

    /**推荐码*/
    @ApiModelProperty(value = "推荐码")
    private java.lang.String referralCode;

    /**来源*/
    @ApiModelProperty(value = "来源")
    private java.lang.String source;

    /**状态(0:正常、1:冻结、2:注销)*/
    @ApiModelProperty(value = "状态(0:正常、1:冻结、2:注销)")
    private java.lang.Integer status;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;

    @ApiModelProperty(value = "邀请人ID")
    private  Long inviteId;

    @ApiModelProperty(value = "邀请人UID")
    private  Long inviteUnid;

    @ApiModelProperty(value = "邀请人Email")
    private  String inviteEmail;
}