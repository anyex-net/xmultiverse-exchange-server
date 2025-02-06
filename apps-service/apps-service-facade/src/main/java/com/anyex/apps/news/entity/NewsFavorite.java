/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.news.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资讯收藏 实体对象
 * <p>File：NewsFavorite.java</p>
 * <p>Title: NewsFavorite</p>
 * <p>Description:NewsFavorite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资讯收藏")
public class NewsFavorite extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**资讯Id*/
	@NotNull(message = "资讯Id不可为空")
	@ApiModelProperty(value = "资讯Id", required = true)
	private Long newsId;

	/**收藏用户Id*/
	@NotNull(message = "收藏用户Id不可为空")
	@ApiModelProperty(value = "收藏用户Id", required = true)
	private Long favoriteAccountId;

	/**位置经度*/
	@ApiModelProperty(value = "位置经度")
	private String lng;

	/**位置维度*/
	@ApiModelProperty(value = "位置维度")
	private String lat;

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

