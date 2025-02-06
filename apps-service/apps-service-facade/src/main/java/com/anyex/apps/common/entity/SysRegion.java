/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 区域代码 实体对象
 * <p>File：Region.java</p>
 * <p>Title: Region</p>
 * <p>Description:Region</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "区域代码")
public class SysRegion extends GenericEntity
{
    private static final long serialVersionUID = 1L;

    /**国际简码*/
    @ApiModelProperty(value = "国际简码")
    private String            sCode;
    
    /**国际代码*/
    @ApiModelProperty(value = "国际代码")
    private String            lCode;
    
    /**英文名称*/
    @ApiModelProperty(value = "英文名称")
    private String            enName;
    
    /**中文名称*/
    @ApiModelProperty(value = "中文名称")
    private String            cnName;
    
    /**区域*/
    @ApiModelProperty(value = "区域")
    private String            area;
    
    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Long              sortNum;
}
