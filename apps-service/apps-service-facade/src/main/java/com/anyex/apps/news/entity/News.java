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
 * 资讯 实体对象
 * <p>File：News.java</p>
 * <p>Title: News</p>
 * <p>Description:News</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资讯")
public class News extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**类别(policy政策、industry行业、platform平台)*/
	@NotEmpty(message = "类别(policy政策、industry行业、platform平台)不可为空")
	@ApiModelProperty(value = "类别(policy政策、industry行业、platform平台)", required = true)
	private String category;

	/**标题*/
	@NotEmpty(message = "标题不可为空")
	@ApiModelProperty(value = "标题", required = true)
	private String title;

	/**摘要*/
	@NotEmpty(message = "摘要不可为空")
	@ApiModelProperty(value = "摘要", required = true)
	private String summary;

	/**主题图片*/
	@NotEmpty(message = "主题图片不可为空")
	@ApiModelProperty(value = "主题图片", required = true)
	private String topicImage;

	/**内容*/
	@NotEmpty(message = "内容不可为空")
	@ApiModelProperty(value = "内容", required = true)
	private String content;

	/**关键字*/
	@NotEmpty(message = "关键字不可为空")
	@ApiModelProperty(value = "关键字", required = true)
	private String keyword;

	/**是否轮播图(0否、1是)*/
	@NotNull(message = "是否轮播图(0否、1是)不可为空")
	@ApiModelProperty(value = "是否轮播图(0否、1是)", required = true)
	private Integer isBanner;

	/**是否固定(0否、1是)*/
	@NotNull(message = "是否固定(0否、1是)不可为空")
	@ApiModelProperty(value = "是否固定(0否、1是)", required = true)
	private Integer isFixed;

	/**状态(0待发布、1已发布)*/
	@NotNull(message = "状态(0待发布、1已发布)不可为空")
	@ApiModelProperty(value = "状态(0待发布、1已发布)", required = true)
	private Integer state;

	/**浏览数量*/
	@NotNull(message = "浏览数量不可为空")
	@ApiModelProperty(value = "浏览数量", required = true)
	private Integer viewNum;

	/**收藏数量*/
	@NotNull(message = "收藏数量不可为空")
	@ApiModelProperty(value = "收藏数量", required = true)
	private Integer favoriteNum;

	/**点赞数量*/
	@NotNull(message = "点赞数量不可为空")
	@ApiModelProperty(value = "点赞数量", required = true)
	private Integer likeNum;

	/**评论数量*/
	@NotNull(message = "评论数量不可为空")
	@ApiModelProperty(value = "评论数量", required = true)
	private Integer commentNum;

	/**分享数量*/
	@NotNull(message = "分享数量不可为空")
	@ApiModelProperty(value = "分享数量", required = true)
	private Integer shareNum;

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

	/**创建人姓名*/
	@NotEmpty(message = "创建人姓名不可为空")
	@ApiModelProperty(value = "创建人姓名", required = true)
	private String createName;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/**发布时间*/
	@ApiModelProperty(value = "发布时间")
	private Long publishTime;

	/**发布人姓名*/
	@ApiModelProperty(value = "发布人姓名")
	private String publishName;


}

