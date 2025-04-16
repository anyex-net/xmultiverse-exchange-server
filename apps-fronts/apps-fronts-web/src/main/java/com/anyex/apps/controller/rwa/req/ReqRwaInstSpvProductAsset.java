/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品资产信息 实体请求对象
 * <p>File：ReqRwaInstSpvProductAsset.java</p>
 * <p>Title: ReqRwaInstSpvProductAsset</p>
 * <p>Description:ReqRwaInstSpvProductAsset</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品资产信息请求对象")
public class ReqRwaInstSpvProductAsset extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@NotNull(message = "用户ID不可为空")
//	@ApiModelProperty(value = "用户ID", position = 1, required = true)
//	private Long userId;
//
//	/**机构投资者ID*/
//	@ApiModelProperty(value = "机构投资者ID", position = 2)
//	private Long instInvestorId;

	/**机构SPV产品ID*/
	@NotNull(message = "机构SPV产品ID不可为空")
	@ApiModelProperty(value = "机构SPV产品ID", position = 3, required = true)
	private Long instSpvProductId;

	/**发行人持有量*/
	@NotNull(message = "发行人持有量不可为空")
	@ApiModelProperty(value = "发行人持有量", position = 4, required = true)
	private java.math.BigDecimal productAmount;

	/**投资人持有量*/
	@NotNull(message = "投资人持有量不可为空")
	@ApiModelProperty(value = "投资人持有量", position = 5, required = true)
	private java.math.BigDecimal investorAmount;

	/**总融资*/
	@NotNull(message = "总融资不可为空")
	@ApiModelProperty(value = "总融资", position = 6, required = true)
	private java.math.BigDecimal totalAmount;

	/**已解冻*/
	@NotNull(message = "已解冻不可为空")
	@ApiModelProperty(value = "已解冻", position = 7, required = true)
	private java.math.BigDecimal amount;

	/**申请解冻*/
	@NotNull(message = "申请解冻不可为空")
	@ApiModelProperty(value = "申请解冻", position = 8, required = true)
	private java.math.BigDecimal lastAmount;

//	/**状态(0审核中、1审核通过、2已驳回)*/
//	@NotNull(message = "状态(0审核中、1审核通过、2已驳回)不可为空")
//	@ApiModelProperty(value = "状态(0审核中、1审核通过、2已驳回)", position = 9, required = true)
//	private Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private String remark;
//
//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 11, required = true)
//	private Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 12)
//	private String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 13)
//	private Long updateTime;


}

