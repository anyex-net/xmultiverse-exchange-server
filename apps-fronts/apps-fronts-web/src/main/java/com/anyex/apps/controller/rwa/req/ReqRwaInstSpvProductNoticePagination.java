/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品公告 分页请求对象
 * <p>File：ReqRwaInstSpvProductNotice.java</p>
 * <p>Title: ReqRwaInstSpvProductNotice</p>
 * <p>Description:ReqRwaInstSpvProductNotice</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品公告分页请求对象")
public class ReqRwaInstSpvProductNoticePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@ApiModelProperty(value = "用户ID", position = 1)
//	private Long userId;
//
//	/**机构投资者ID*/
//	@ApiModelProperty(value = "机构投资者ID", position = 2)
//	private Long instInvestorId;

	/**机构SPV产品ID*/
	@ApiModelProperty(value = "机构SPV产品ID", position = 3)
	private Long instSpvProductId;

	/**公告标题*/
	@ApiModelProperty(value = "公告标题", position = 4)
	private String noticeTitle;

	/**公告内容*/
	@ApiModelProperty(value = "公告内容", position = 5)
	private String noticeContent;

	/**审核意见*/
	@ApiModelProperty(value = "审核意见", position = 6)
	private String checkOpinion;

	/**状态(0审核中、1已驳回、2待发布、3已发布)*/
	@ApiModelProperty(value = "状态(0审核中、1已驳回、2待发布、3已发布)", position = 7)
	private Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 8)
	private String remark;
//
//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 9)
//	private Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 10)
//	private String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 11)
//	private Long updateTime;


}

