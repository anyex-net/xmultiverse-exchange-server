/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;

@Data
@ApiModel(description = "活动操作分页请求对象")
public class ReqActivityOperRecordPagination extends Pagination
{
	/**商品活动ID*/
	@ApiModelProperty(value = "商品活动ID")
	private Long activityId;

	/**商品活动类型(TreasureHunt、HotDeals等)*/
	@ApiModelProperty(value = "商品活动类型(TreasureHunt、HotDeals等)")
	private String activityType;

	/**请求IP*/
	@ApiModelProperty(value = "请求IP")
	private String requestIp;

	/**账户Id*/
	@ApiModelProperty(value = "账户Id")
	private Long accountId;

	/**操作类型(浏览browse、favorite收藏、praise点赞、comment评论)*/
	@ApiModelProperty(value = "操作类型(浏览browse、favorite收藏、praise点赞、comment评论)")
	private String operType;

	/**操作内容(评论内容)*/
	@ApiModelProperty(value = "操作内容(评论内容)")
	private String operContent;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;
}

