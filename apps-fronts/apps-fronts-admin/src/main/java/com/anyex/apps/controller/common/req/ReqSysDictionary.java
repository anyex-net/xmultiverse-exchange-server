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
@ApiModel(description = "数据字典")
public class ReqSysDictionary extends GenericEntity
{
    private static final long serialVersionUID = 1L;

    /**上级编码ID*/
    @ApiModelProperty(value = "上级编码ID")
    private Long              parentId;

    /**编码*/
    @NotNull(message = "编码不可为空")
    @ApiModelProperty(value = "编码", required = true)
    private String            code;

    /**名称*/
    @NotNull(message = "名称不可为空")
    @ApiModelProperty(value = "名称", required = true)
    private String            name;

    /**语言*/
    @NotNull(message = "语言不可为空")
    @ApiModelProperty(value = "语言", required = true)
    private String            lang;

    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            dest;

    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Long              sortNum;

    /**启用标识*/
    @NotNull(message = "启用标识不可为空")
    @ApiModelProperty(value = "启用标识", required = true)
    private Boolean           active;
}
