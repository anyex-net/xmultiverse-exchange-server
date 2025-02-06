/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 邮件、短信记录表 实体对象
 * <p>File：MsgRecord.java</p>
 * <p>Title: MsgRecord</p>
 * <p>Description:MsgRecord</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
public class SysMsgRecord extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**消息类型*/
    @NotNull(message = "消息类型(email:邮件、sms:短信)")
    @ApiModelProperty(value = "消息类型(email:邮件、sms:短信)", required = true)
    private String            type;
    
    /**手机号*/
    @NotNull(message = "发送对象")
    @ApiModelProperty(value = "发送对象", required = true)
    private String            object;
    
    /**内容*/
    @NotNull(message = "内容不可为空")
    @ApiModelProperty(value = "内容", required = true)
    private String            content;
    
    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    private Long              createDate;
    
    /**发送状态（0：成功，1：失败）*/
    @NotNull(message = "发送状态（0：成功，1：失败）不可为空")
    @ApiModelProperty(value = "发送状态（0：成功，1：失败）不可为空", required = true)
    private Boolean           status;

    ///////////////////////////////////
    /**用户界面传值 查询开始时间 */
    private Long              timeStart;

    /**用户界面传值 查询结束时间 */
    private Long              timeEnd;
    
    public SysMsgRecord()
    {
    }
    
    public SysMsgRecord(String type, String object, String content)
    {
        this.type = type;
        this.object = object;
        this.content = content;
    }
    
    public SysMsgRecord(String type, String object, String content, Boolean status)
    {
        this.type = type;
        this.object = object;
        this.content = content;
        this.status = status;
    }
}
