/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会话限制 实体对象
 * <p>File：ConversationLimit.java</p>
 * <p>Title: ConversationLimit</p>
 * <p>Description:ConversationLimit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "会话限制")
public class ConversationLimit extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**会话ID*/
	@NotEmpty(message = "会话ID不可为空")
	@ApiModelProperty(value = "会话ID", position = 1, required = true)
	private String conversationId;

	/**发送人ID*/
	@NotEmpty(message = "发送人ID不可为空")
	@ApiModelProperty(value = "发送人ID", position = 2, required = true)
	private String fromUserId;

	/**接收人ID*/
	@NotEmpty(message = "接收人ID不可为空")
	@ApiModelProperty(value = "接收人ID", position = 3, required = true)
	private String toUserId;

	/**用户ID*/
	@NotEmpty(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 4, required = true)
	private String userId;

	/**消息序号*/
	@NotNull(message = "消息序号不可为空")
	@ApiModelProperty(value = "消息序号", position = 5, required = true)
	private Integer msgSeq;

	/**状态 0有限制(>3条不能发)，1不限制*/
	@NotNull(message = "状态 0有限制(>3条不能发)，1不限制不可为空")
	@ApiModelProperty(value = "状态 0有限制(>3条不能发)，1不限制", position = 6, required = true)
	private Integer status;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 7)
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 8, required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 9)
	private Long updateTime;


}

