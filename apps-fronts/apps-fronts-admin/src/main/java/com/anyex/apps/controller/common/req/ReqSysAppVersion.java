/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "APP版本")
public class ReqSysAppVersion extends GenericEntity
{
    private static final long serialVersionUID = 1L;

    /**设备类型*/
    @NotNull(message = "设备类型不可为空")
    @ApiModelProperty(value = "设备类型", required = true)
    private String            deviceType;

    /**版本号*/
    @NotNull(message = "版本号不可为空")
    @ApiModelProperty(value = "版本号", required = true)
    private String            appVersion;

    /**build版本号*/
    @NotNull(message = "build版本号不可为空")
    @ApiModelProperty(value = "build版本号", required = true)
    private String            buildVersion;
    
    /**是否支持(true、false)*/
    @NotNull(message = "是否支持(true、false)不可为空")
    @ApiModelProperty(value = "是否支持(true、false)", required = true)
    private Boolean           canSupport;

    /**审核状态(true、false)*/
    @NotNull(message = "审核状态(true、false)不可为空")
    @ApiModelProperty(value = "审核状态(true、false)", required = true)
    private Boolean           checkStatus;
    
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String            remark;
}
