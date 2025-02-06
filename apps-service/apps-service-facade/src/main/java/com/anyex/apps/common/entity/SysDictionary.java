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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据字典 实体对象
 * <p>File：Dictionary.java</p>
 * <p>Title: Dictionary</p>
 * <p>Description:Dictionary</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "数据字典")
public class SysDictionary extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**上级编码ID*/
    @ApiModelProperty(value = "上级编码ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              parentId;
    
    /**编码*/
    @NotNull(message = "编码不可为空")
    @ApiModelProperty(value = "编码")
    private String            code;
    
    /**名称*/
    @NotNull(message = "名称不可为空")
    @ApiModelProperty(value = "名称")
    private String            name;
    
    /**语言*/
    @ApiModelProperty(value = "语言")
    private String            lang;
    
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            dest;
    
    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Long              sortNum;
    
    /**启用标识*/
    @NotNull(message = "启用标识不可为空")
    @ApiModelProperty(value = "启用标识")
    private Boolean           active;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;
    
    @ApiModelProperty(value = "子节点")
    private List<SysDictionary>  children;
    
    public SysDictionary(Long parentId)
    {
        this.parentId = parentId;
    }
}
