/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品公告 实体对象
 * <p>File：RwaInstSpvProductNotice.java</p>
 * <p>Title: RwaInstSpvProductNotice</p>
 * <p>Description:RwaInstSpvProductNotice</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品公告")
public class RwaInstSpvProductNotice extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**机构投资者ID*/
	@NotNull(message = "机构投资者ID不可为空")
	@ApiModelProperty(value = "机构投资者ID", position = 2, required = true)
	private Long instInvestorId;

	/**机构SPV产品ID*/
	@NotNull(message = "机构SPV产品ID不可为空")
	@ApiModelProperty(value = "机构SPV产品ID", position = 3, required = true)
	private Long instSpvProductId;

	/**公告标题*/
	@NotEmpty(message = "公告标题不可为空")
	@ApiModelProperty(value = "公告标题", position = 4, required = true)
	private String noticeTitle;

	/**公告内容*/
	@NotEmpty(message = "公告内容不可为空")
	@ApiModelProperty(value = "公告内容", position = 5, required = true)
	private String noticeContent;

	/**审核意见*/
	@ApiModelProperty(value = "审核意见", position = 6)
	private String checkOpinion;

	/**状态(0审核中、1已驳回、2待发布、3已发布)*/
	@NotNull(message = "状态(0审核中、1已驳回、2待发布、3已发布)不可为空")
	@ApiModelProperty(value = "状态(0审核中、1已驳回、2待发布、3已发布)", position = 7, required = true)
	private Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 8)
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 9, required = true)
	private Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 10)
	private String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 11)
	private Long updateTime;


}

