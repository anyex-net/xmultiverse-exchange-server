/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system.req;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "资源菜单信息")
public class ReqSysResources extends GenericEntity
{
    private static final long serialVersionUID = 1L;

    /**上级编号ID*/
    @NotNull(message = "上级编号ID不可为空")
    @ApiModelProperty(value = "上级编号ID", required = true)
    private Long              parentId;

    /**资源编码*/
    @NotNull(message = "资源编码不可为空")
    @ApiModelProperty(value = "资源编码", required = true)
    private String            resCode;

    /**资源名称*/
    @NotNull(message = "资源名称不可为空")
    @ApiModelProperty(value = "资源名称", required = true)
    private String            resName;

    /**资源描述*/
    @ApiModelProperty(value = "资源描述")
    private String            resDest;

    /**类型（菜单0、权限1）*/
    @ApiModelProperty(value = "类型（菜单0、权限1）", required = true)
    private Boolean           type;

    /**图标*/
    @JsonProperty("iconCls")
    @ApiModelProperty(value = "图标")
    private String            icon;

    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Integer           sortNum;

    /**资源短地址*/
    @NotEmpty(message = "资源短地址不可为空")
    @ApiModelProperty(value = "资源短地址", required = true)
    private String            resShortUrl;

    /**资源地址*/
    @NotEmpty(message = "资源地址不可为空")
    @ApiModelProperty(value = "资源地址", required = true)
    private String            resUrl;
}
