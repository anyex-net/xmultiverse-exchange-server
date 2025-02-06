/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 资源菜单信息表 实体对象
 * <p>File：Resources.java</p>
 * <p>Title: Resources</p>
 * <p>Description:Resources</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "资源菜单信息")
public class SysResources extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /** 上级编号*/
    @ApiModelProperty(value = "上级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              parentId;
    
    /**资源编码*/
    @NotNull(message = "资源编码不可为空")
    @ApiModelProperty(value = "资源编码")
    private String            resCode;
    
    /**资源名称*/
    @NotNull(message = "资源名称不可为空")
    @ApiModelProperty(value = "资源名称")
    private String            resName;
    
    /**资源描述*/
    @ApiModelProperty(value = "资源描述")
    private String            resDest;
    
    /**类型（菜单、权限）*/
    @ApiModelProperty(value = "类型（菜单、权限）")
    private Boolean           type;
    
    /**图标*/
    @JsonProperty("iconCls")
    @ApiModelProperty(value = "图标")
    private String            icon;
    
    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Integer           sortNum;

    /**资源短地址*/
    @NotNull(message = "资源短地址不可为空")
    @ApiModelProperty(value = "资源短地址")
    private String            resShortUrl;

    /**资源地址*/
    @NotNull(message = "资源地址不可为空")
    @ApiModelProperty(value = "资源地址")
    private String            resUrl;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              createBy;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private String            createByName;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;
    
    /**修改人*/
    @ApiModelProperty(value = "修改人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              updateBy;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long              updateDate;

    ///////////////////////////////////
    /**子节点*/
    @ApiModelProperty(value = "子节点")
    private List<SysResources>   children;
    
    public SysResources(Long parentId)
    {
        this.parentId = parentId;
    }
}
