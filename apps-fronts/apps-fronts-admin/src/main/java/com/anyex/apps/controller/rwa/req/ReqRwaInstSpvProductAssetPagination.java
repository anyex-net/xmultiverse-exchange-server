/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品资产信息 分页请求对象
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
@ApiModel(description = "RWA机构SPV产品资产信息分页请求对象")
public class ReqRwaInstSpvProductAssetPagination extends Pagination
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

	/**发行人持有量*/
	@ApiModelProperty(value = "发行人持有量", position = 4)
	private java.math.BigDecimal productAmount;

	/**投资人持有量*/
	@ApiModelProperty(value = "投资人持有量", position = 5)
	private java.math.BigDecimal investorAmount;

	/**总融资*/
	@ApiModelProperty(value = "总融资", position = 6)
	private java.math.BigDecimal totalAmount;

	/**已解冻*/
	@ApiModelProperty(value = "已解冻", position = 7)
	private java.math.BigDecimal amount;

	/**申请解冻*/
	@ApiModelProperty(value = "申请解冻", position = 8)
	private java.math.BigDecimal lastAmount;

	/**状态(0审核中、1审核通过、2已驳回)*/
	@ApiModelProperty(value = "状态(0审核中、1审核通过、2已驳回)", position = 9)
	private Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 11)
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

