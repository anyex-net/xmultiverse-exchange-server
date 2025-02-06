/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * APP版本请求对象
 * <p>File：ReqAppVersion.java</p>
 * <p>Title: ReqAppVersion</p>
 * <p>Description:ReqAppVersion</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author yukai
 * @version 1.0
 */
@Data
@ApiModel(description = "APP版本请求对象")
public class ReqAppVersion implements Serializable
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

    /**buildVersion版本号*/
    @NotNull(message = "buildVersion版本号不可为空")
    @ApiModelProperty(value = "buildVersion版本号", required = true)
    private String            buildVersion;
}
