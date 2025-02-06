/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "角色信息")
public class ReqSysRoleInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**角色编码*/
    @NotNull(message = "角色编码不可为空")
    @ApiModelProperty(value = "角色编码", required = true)
    private String            roleCode;
    
    /**角色名称*/
    @NotNull(message = "角色名称不可为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String            roleName;
    
    /**角色描述*/
    @ApiModelProperty(value = "角色描述", required = true)
    private String            roleDest;

    /**是否需要绑定GA 1需要 0不需要*/
    @NotNull(message = "是否需要绑定GA不可为空")
    @ApiModelProperty(value = "是否需要绑定GA 1需要 0不需要", required = true)
    private Boolean           needGa;
}
