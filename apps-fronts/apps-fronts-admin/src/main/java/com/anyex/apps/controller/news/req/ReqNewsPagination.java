/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.news.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资讯 分页请求对象
 * <p>File：ReqNews.java</p>
 * <p>Title: ReqNews</p>
 * <p>Description:ReqNews</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资讯分页请求对象")
public class ReqNewsPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**类别(policy政策、industry行业、platform平台)*/
	@ApiModelProperty(value = "类别(policy政策、industry行业、platform平台)")
	private java.lang.String category;

	/**标题*/
	@ApiModelProperty(value = "标题")
	private java.lang.String title;

	/**摘要*/
	@ApiModelProperty(value = "摘要")
	private java.lang.String summary;

	/**主题图片*/
	@ApiModelProperty(value = "主题图片")
	private java.lang.String topicImage;

	/**内容*/
	@ApiModelProperty(value = "内容")
	private java.lang.String content;

	/**关键字*/
	@ApiModelProperty(value = "关键字")
	private java.lang.String keyword;

	/**是否轮播图(0否、1是)*/
	@ApiModelProperty(value = "是否轮播图(0否、1是)")
	private java.lang.Integer isBanner;

	/**是否固定(0否、1是)*/
	@ApiModelProperty(value = "是否固定(0否、1是)")
	private java.lang.Integer isFixed;

	/**状态(0待发布、1已发布)*/
	@ApiModelProperty(value = "状态(0待发布、1已发布)")
	private java.lang.Integer state;

	/**浏览数量*/
	@ApiModelProperty(value = "浏览数量")
	private java.lang.Integer viewNum;

	/**收藏数量*/
	@ApiModelProperty(value = "收藏数量")
	private java.lang.Integer favoriteNum;

	/**点赞数量*/
	@ApiModelProperty(value = "点赞数量")
	private java.lang.Integer likeNum;

	/**评论数量*/
	@ApiModelProperty(value = "评论数量")
	private java.lang.Integer commentNum;

	/**分享数量*/
	@ApiModelProperty(value = "分享数量")
	private java.lang.Integer shareNum;

	/**位置经度*/
	@ApiModelProperty(value = "位置经度")
	private java.lang.String lng;

	/**位置维度*/
	@ApiModelProperty(value = "位置维度")
	private java.lang.String lat;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**创建人姓名*/
	@ApiModelProperty(value = "创建人姓名")
	private java.lang.String createName;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	/**发布时间*/
	@ApiModelProperty(value = "发布时间")
	private java.lang.Long publishTime;

	/**发布人姓名*/
	@ApiModelProperty(value = "发布人姓名")
	private java.lang.String publishName;
}

