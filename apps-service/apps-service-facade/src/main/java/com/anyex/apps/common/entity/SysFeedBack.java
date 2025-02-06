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
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * FeedBack 实体对象
 * <p>File：FeedBack.java</p>
 * <p>Title: FeedBack</p>
 * <p>Description:FeedBack</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "意见反馈")
public class SysFeedBack extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**帐户编号*/
    @NotNull(message = "帐户编号不可为空")
    @ApiModelProperty(value = "帐户编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              accountId;
    
    /**反馈内容*/
    @NotNull(message = "反馈内容不可为空")
    @ApiModelProperty(value = "反馈内容", required = true)
    private String            note;
    
    /**设备信息*/
    @ApiModelProperty(value = "设备信息")
    private String            ext;
    
    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    private Long              createDate;
    
    public SysFeedBack()
    {
    }
    
    public SysFeedBack(Long accountId)
    {
        this.accountId = accountId;
    }
}
