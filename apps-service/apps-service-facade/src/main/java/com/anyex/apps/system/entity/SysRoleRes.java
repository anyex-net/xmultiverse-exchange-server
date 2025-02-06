/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 角色权限信息表 实体对象
 * <p>File：RoleRes.java</p>
 * <p>Title: RoleRes</p>
 * <p>Description:RoleRes</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "角色权限信息")
public class SysRoleRes extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**角色ID*/
    @NotNull(message = "角色ID不可为空")
    @ApiModelProperty(value = "角色ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              roleId;
    
    /**资源ID*/
    @NotNull(message = "资源ID不可为空")
    @ApiModelProperty(value = "资源ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              resId;
    
    public SysRoleRes(Long roleId, Long resId)
    {
        this.roleId = roleId;
        this.resId = resId;
    }
}
