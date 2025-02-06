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
 * 消息模版 实体对象
 * <p>File：MsgTemplate.java</p>
 * <p>Title: MsgTemplate</p>
 * <p>Description:MsgTemplate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "消息模版")
public class SysMsgTemplate extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**模版KEY*/
    @NotNull(message = "模版KEY不可为空")
    @ApiModelProperty(value = "模版KEY", required = true)
    private String            tplKey;
    
    /**语言编码（en_US,zh_CN,zh_HK)*/
    @NotNull(message = "语言编码")
    @ApiModelProperty(value = "语言编码（en_US,zh_CN,zh_HK)", required = true)
    private String            lang;
    
    /**模版类型(email:邮件、sms:短信)*/
    @NotNull(message = "模版类型不可为空")
    @ApiModelProperty(value = "模版类型(email:邮件、sms:短信)", required = true)
    private String            type;
    
    /**标题*/
    @NotNull(message = "消息标题")
    @ApiModelProperty(value = "消息标题", required = true)
    private String            title;
    
    /**模版内容*/
    @NotNull(message = "模版内容不可为空")
    @ApiModelProperty(value = "模版内容", required = true)
    private String            content;
    
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            dest;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;

    ///////////////////////////////////
//    /**创建人*/
//    private String            createName;
}
