/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "用户信息")
public class ReqAuthUserInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**用户名*/
    @NotEmpty(message = "用户名不可为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String            userName;
    
    /**真实姓名*/
    @ApiModelProperty(value = "真实姓名", required = true)
    private String            trueName;

    /**手机号*/
    @NotEmpty(message = "手机号不可为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String            phone;
    
    /**性别(0男、1女)*/
    @ApiModelProperty(value = "性别(0男、1女)", required = true)
    private Boolean           gender;
    
    /**身份证号*/
    @ApiModelProperty(value = "身份证号")
    private String            idCard;
    
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            userDesc;
}
