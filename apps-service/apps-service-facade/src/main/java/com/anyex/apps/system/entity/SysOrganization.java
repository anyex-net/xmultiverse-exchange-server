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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 机构信息表 实体对象
 * <p>File：Organization.java</p>
 * <p>Title: Organization</p>
 * <p>Description:Organization</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel(description = "机构信息")
public class SysOrganization extends GenericEntity
{
    private static final long  serialVersionUID = 1L;
    
    /**上级编号*/
    @ApiModelProperty(value = "上级ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long               parentId;
    
    /**机构编码*/
    @NotNull(message = "机构编码不可为空")
    @ApiModelProperty(value = "机构编码", required = true)
    private String             orgCode;
    
    /**机构名称*/
    @NotNull(message = "机构名称不可为空")
    @ApiModelProperty(value = "机构名称", required = true)
    private String             orgName;
    
    /**机构描述*/
    @ApiModelProperty(value = "机构描述", required = true)
    private String             orgDest;
    
    /**排序号*/
    @ApiModelProperty(value = "排序号", required = true)
    private Long               sortNum;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String             remark;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long               createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long               createDate;
    
    /**修改人*/
    @ApiModelProperty(value = "修改人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long               updateBy;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long               updateDate;

//    /**机构组织人数*/
//    @ApiModelProperty(value = "机构组织人数")
//    private Long               userNum;
//
//    /**联系方式*/
//    @ApiModelProperty(value = "联系方式")
//    private String             contact;

    ///////////////////////////////////
    /**子节点*/
    @ApiModelProperty(value = "子节点")
    private List<SysOrganization> children;

    /**创建人*/
    @ApiModelProperty(value = "创建人姓名")
    private String             createByName;

//    /**机构下用户*/
//    private List<SysUserInfo> userInfoList;

    public SysOrganization(Long parentId)
    {
        this.parentId = parentId;
    }
}
