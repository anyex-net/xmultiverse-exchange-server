/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 用户角色权限表 实体对象
 * <p>File：UserRole.java</p>
 * <p>Title: UserRole</p>
 * <p>Description:UserRole</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "用户角色权限")
public class SysUserRole extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**角色ID*/
    @NotNull(message = "角色ID不可为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              roleId;
    
    /**用户ID*/
    @NotNull(message = "用户ID不可为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              userId;
    
    public SysUserRole()
    {
    }
    
    public SysUserRole(Long roleId, Long userId)
    {
        this.roleId = roleId;
        this.userId = userId;
    }
}
