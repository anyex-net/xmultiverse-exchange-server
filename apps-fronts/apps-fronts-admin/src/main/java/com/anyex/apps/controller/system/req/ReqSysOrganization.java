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
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "机构信息")
public class ReqSysOrganization extends GenericEntity
{
    private static final long  serialVersionUID = 1L;

    /**上级编号*/
    @ApiModelProperty(value = "上级ID", required = true)
    private Long               parentId;

    /**机构编码*/
    @NotEmpty(message = "机构编码不可为空")
    @ApiModelProperty(value = "机构编码", required = true)
    private String             orgCode;

    /**机构名称*/
    @NotEmpty(message = "机构名称不可为空")
    @ApiModelProperty(value = "机构名称", required = true)
    private String             orgName;

    /**机构描述*/
    @NotEmpty(message = "机构描述不可为空")
    @ApiModelProperty(value = "机构描述", required = true)
    private String             orgDest;

    /**排序号*/
    @NotNull(message = "排序号不可为空")
    @ApiModelProperty(value = "排序号", required = true)
    private Long               sortNum;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String             remark;
}
