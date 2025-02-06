/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.SignableEntity;
import com.anyex.apps.consts.CharsetConst;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 用户基础信息表 实体对象
 * <p>File：UserInfo.java</p>
 * <p>Title: UserInfo</p>
 * <p>Description:UserInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "用户基础信息")
public class SysUserInfo extends SignableEntity
{
    private static final long serialVersionUID = 1L;
    
    /**机构ID*/
    @NotNull(message = "机构ID不可为空")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "机构ID", required = true)
    private Long              orgId;

    /**机构名称*/
    @ApiModelProperty(value = "机构名称")
    private String            orgName;
    
    /**用户名*/
    @NotNull(message = "用户名不可为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String            userName;
    
    /**真实姓名*/
    @ApiModelProperty(value = "真实姓名")
    private String            trueName;
    
    /**密码*/
    @JsonIgnore
    @ApiModelProperty(value = "密码")
    private String            passWord;

    /**手机号*/
    @NotNull(message = "手机号不可为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String            phone;
    
    /**性别*/
    @ApiModelProperty(value = "性别")
    private Boolean           gender;
    
    /**激活状态*/
    @ApiModelProperty(value = "激活状态")
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
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long              updateDate;

    ////////////////////////////////////
    private List<SysRoleInfo>    roleList;

    private String            roleIds; // 角色ids

    private String            permission; // 权限字符串

    private String            orderBy;
    
    @Override
    protected byte[] acquiresSignValue() throws UnsupportedEncodingException
    {
        String sign = new StringBuffer(String.valueOf(orgId)).append(userName).append(passWord).toString();
        return sign.getBytes(CharsetConst.CHARSET_UT);
    }
    
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("id=").append(id);
        sb.append(", orgId='").append(orgId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", trueName='").append(trueName).append('\'');
        sb.append(", passWord='").append(passWord).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", active=").append(active);
        sb.append(", idCard='").append(idCard).append('\'');
        sb.append(", authKey='").append(authKey).append('\'');
        sb.append(", roleIds='").append(roleIds).append('\'');
        sb.append(", userLogo='").append(userLogo).append('\'');
        sb.append(", userDesc='").append(userDesc).append('\'');
        sb.append(", jobTitle='").append(jobTitle).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", zzdOpenId='").append(zzdOpenId).append('\'');
        sb.append(", zzdName='").append(zzdName).append('\'');
        sb.append(", wxOpenId='").append(wxOpenId).append('\'');
        sb.append(", wxName='").append(wxName).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append('}');
        return sb.toString();
    }
}
