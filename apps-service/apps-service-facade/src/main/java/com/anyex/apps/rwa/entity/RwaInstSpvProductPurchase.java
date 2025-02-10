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
 * RWA机构SPV产品申购记录 实体对象
 * <p>File：RwaInstSpvProductPurchase.java</p>
 * <p>Title: RwaInstSpvProductPurchase</p>
 * <p>Description:RwaInstSpvProductPurchase</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品申购记录")
public class RwaInstSpvProductPurchase extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private java.lang.Long userId;

	/**机构投资者ID*/
	@ApiModelProperty(value = "机构投资者ID", position = 2)
	private java.lang.Long instInvestorId;

	/**机构SPV产品ID*/
	@NotNull(message = "机构SPV产品ID不可为空")
	@ApiModelProperty(value = "机构SPV产品ID", position = 3, required = true)
	private java.lang.Long instSpvProductId;

	/**申购币种*/
	@NotEmpty(message = "申购币种不可为空")
	@ApiModelProperty(value = "申购币种", position = 4, required = true)
	private java.lang.String purchaseCurrency;

	/**申购价格*/
	@NotNull(message = "申购价格不可为空")
	@ApiModelProperty(value = "申购价格", position = 5, required = true)
	private java.math.BigDecimal purchasePrice;

	/**申购数量*/
	@NotNull(message = "申购数量不可为空")
	@ApiModelProperty(value = "申购数量", position = 6, required = true)
	private java.math.BigDecimal purchaseAmount;

	/**状态(成功success、处理中pending、失败failed)*/
	@NotEmpty(message = "状态(成功success、处理中pending、失败failed)不可为空")
	@ApiModelProperty(value = "状态(成功success、处理中pending、失败failed)", position = 7, required = true)
	private java.lang.String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 8)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 9, required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 10)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 11)
	private java.lang.Long updateTime;
}

