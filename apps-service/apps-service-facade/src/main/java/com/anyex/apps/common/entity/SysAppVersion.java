/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.entity;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 手机端版本表 实体对象
 * <p>File：AppVersion.java</p>
 * <p>Title: AppVersion</p>
 * <p>Description:AppVersion</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author yukai
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "手机端版本表")
public class SysAppVersion extends GenericEntity
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
    @NotNull(message = "是否支持(yes、no)不可为空")
    @ApiModelProperty(value = "是否支持(true、false)", required = true)
    private Boolean           canSupport;

    /**审核状态(true、false)*/
    @NotNull(message = "审核状态(yes、no)不可为空")
    @ApiModelProperty(value = "审核状态(true、false)", required = true)
    private Boolean           checkStatus;
    
    /**创建人*/
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人", required = true)
    private Long              createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间", required = true)
    private Long              createDate;
    
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String            remark;
}
