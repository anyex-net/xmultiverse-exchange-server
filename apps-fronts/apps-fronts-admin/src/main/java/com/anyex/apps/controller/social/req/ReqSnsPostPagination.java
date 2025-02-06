/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社交帖子 分页请求对象
 * <p>File：ReqSnsPost.java</p>
 * <p>Title: ReqSnsPost</p>
 * <p>Description:ReqSnsPost</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交帖子分页请求对象")
public class ReqSnsPostPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户Id*/
	@ApiModelProperty(value = "用户Id")
	private java.lang.String userId;

	/**帖子文本内容*/
	@ApiModelProperty(value = "帖子文本内容")
	private java.lang.String postTextContent;

	/**帖子图片URL*/
	@ApiModelProperty(value = "帖子图片URL")
	private java.lang.String postImageUrl;

	/**帖子视频URL*/
	@ApiModelProperty(value = "帖子视频URL")
	private java.lang.String postVideoUrl;

	/**0匿名、1公开*/
	@ApiModelProperty(value = "0匿名、1公开")
	private java.lang.Integer openness;

	/**0公开、1仅限好友、2仅限粉丝、3仅限自己*/
	@ApiModelProperty(value = "0公开、1仅限好友、2仅限粉丝、3仅限自己")
	private java.lang.Integer viewer;

	/**位置经度*/
	@ApiModelProperty(value = "位置经度")
	private java.lang.String lng;

	/**位置维度*/
	@ApiModelProperty(value = "位置维度")
	private java.lang.String lat;

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

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;


}

