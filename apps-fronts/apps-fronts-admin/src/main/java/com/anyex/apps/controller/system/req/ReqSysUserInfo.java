/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system.req;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "用户信息")
public class ReqSysUserInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**机构ID*/
    @NotNull(message = "机构ID不可为空")
    @ApiModelProperty(value = "机构ID", required = true)
    private Long              orgId;
    
    /**用户名*/
    @NotEmpty(message = "用户名不可为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String            userName;
    
    /**真实姓名*/
    @ApiModelProperty(value = "真实姓名")
    private String            trueName;
    
    /**密码*/
    @JsonIgnore
    @NotEmpty(message = "密码不可为空")
    @ApiModelProperty(value = "密码", required = true)
    private String            passWord;

    /**手机号*/
    @NotEmpty(message = "手机号不可为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String            phone;
    
    /**性别(0男、1女)*/
    @ApiModelProperty(value = "性别(0男、1女)", required = true)
    private Boolean           gender;
    
    /**激活状态(0未激活、1激活)*/
    @ApiModelProperty(value = "激活状态(0未激活、1激活)", required = true)
    private Boolean           active;
    
    /**身份证号*/
    @ApiModelProperty(value = "身份证号")
    private String            idCard;
    
    /**google验证器私钥*/
    @ApiModelProperty(value = "google验证器私钥")
    private String            authKey;
    
    /**头像*/
    @ApiModelProperty(value = "头像")
    private String            userLogo;
    
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            userDesc;
    
    /**职称*/
    @ApiModelProperty(value = "职称")
    private String            jobTitle;
    
    /**所在地址*/
    @ApiModelProperty(value = "所在地址")
    private String            address;

    /**浙政钉openId*/
    @ApiModelProperty(value = "浙政钉openId")
    private String            zzdOpenId;

    /**浙政钉昵称*/
    @ApiModelProperty(value = "浙政钉昵称")
    private String            zzdName;

    /**微信openId*/
    @ApiModelProperty(value = "微信openId")
    private String            wxOpenId;

    /**微信昵称*/
    @ApiModelProperty(value = "微信昵称")
    private String            wxName;

    /**角色ids(逗号分隔)*/
    @NotEmpty(message = "角色ids(逗号分隔)不可为空")
    @ApiModelProperty(value = "角色ids(逗号分隔)", required = true)
    private String            roleIds;
}
