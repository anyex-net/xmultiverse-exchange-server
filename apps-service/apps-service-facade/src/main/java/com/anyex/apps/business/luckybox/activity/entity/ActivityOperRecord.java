/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 活动操作记录表 实体对象
 * <p>File：ActivityOperRecord.java</p>
 * <p>Title: ActivityOperRecord</p>
 * <p>Description:ActivityOperRecord</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "活动操作记录表")
public class ActivityOperRecord extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**商品活动ID*/
	@NotNull(message = "商品活动ID不可为空")
	@ApiModelProperty(value = "商品活动ID", required = true)
	private Long activityId;

	/**商品活动类型(TreasureHunt、HotDeals等)*/
	@NotNull(message = "商品活动类型(TreasureHunt、HotDeals等)不可为空")
	@ApiModelProperty(value = "商品活动类型(TreasureHunt、HotDeals等)", required = true)
	private String activityType;

	/**请求IP*/
	@ApiModelProperty(value = "请求IP")
	private String requestIp;

	/**账户Id*/
	@ApiModelProperty(value = "账户Id")
	private Long accountId;

	/**操作类型(浏览browse、favorite收藏、praise点赞、comment评论)*/
	@NotNull(message = "操作类型(浏览browse、favorite收藏、praise点赞、comment评论)不可为空")
	@ApiModelProperty(value = "操作类型(浏览browse、favorite收藏、praise点赞、comment评论)", required = true)
	private String operType;

	/**操作内容(评论内容)*/
	@ApiModelProperty(value = "操作内容(评论内容)")
	private String operContent;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;
}

