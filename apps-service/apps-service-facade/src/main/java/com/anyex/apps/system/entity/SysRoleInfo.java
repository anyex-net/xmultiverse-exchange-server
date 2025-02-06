/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.annotation.ExcelField;
import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色信息表 实体对象
 * <p>File：RoleInfo.java</p>
 * <p>Title: RoleInfo</p>
 * <p>Description:RoleInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "角色信息")
public class SysRoleInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**角色编码*/
    @ExcelField(title = "角色编码", align = 3)
    @NotNull(message = "角色编码不可为空")
    @ApiModelProperty(value = "角色编码", required = true)
    private String            roleCode;
    
    /**角色名称*/
    @ExcelField(title = "角色名称", align = 3)
    @NotNull(message = "角色名称不可为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String            roleName;
    
    /**角色描述*/
    @ExcelField(title = "角色描述", align = 3)
    @ApiModelProperty(value = "角色描述", required = true)
    private String            roleDest;

    /**是否需要绑定GA 1需要 0不需要*/
    @ApiModelProperty(value = "是否需要绑定GA 1需要 0不需要", required = true)
    private Boolean           needGa;

    /**创建人*/
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "创建人")
    private Long              createBy;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;

    /**修改人*/
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "修改人")
    private Long              updateBy;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long              updateDate;

    ///////////////////////////////////
    /**创建人姓名*/
    @ApiModelProperty(value = "创建人姓名")
    private String            createByName;

    /**资源信息*/
    @ApiModelProperty(value = "资源信息")
    private List<SysResources> resources;

//    /**移动端功能模块*/
//    List<SysFrontModule>         frontModules;

//    /**数据权限类型*/
//    private String            DataPermissionType;
}
