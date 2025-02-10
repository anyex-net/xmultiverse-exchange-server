/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台币种 分页请求对象
 * <p>File：ReqCurrencies.java</p>
 * <p>Title: ReqCurrencies</p>
 * <p>Description:ReqCurrencies</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "平台币种分页请求对象")
public class ReqCurrenciesPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**币种(BTC、ETH、USDT)*/
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 1)
	private java.lang.String currency;

	/**币种中文名*/
	@ApiModelProperty(value = "币种中文名", position = 2)
	private java.lang.String currencyName;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3)
	private java.lang.String blockchain;

	/**是否可充值 false表示不可链上充值 true表示可以链上充值*/
	@ApiModelProperty(value = "是否可充值 false表示不可链上充值 true表示可以链上充值", position = 4)
	private java.lang.String canDep;

	/**是否可提币 false表示不可链上提币 true表示可以链上提币*/
	@ApiModelProperty(value = "是否可提币 false表示不可链上提币 true表示可以链上提币", position = 5)
	private java.lang.String canWd;

	/**是否可内部转账 false表示不可内部转账 true表示可以内部转账*/
	@ApiModelProperty(value = "是否可内部转账 false表示不可内部转账 true表示可以内部转账", position = 6)
	private java.lang.String canInternal;

	/**币种单笔最小提币量*/
	@ApiModelProperty(value = "币种单笔最小提币量", position = 7)
	private java.math.BigDecimal minWd;

	/**币种单笔最大提币量*/
	@ApiModelProperty(value = "币种单笔最大提币量", position = 8)
	private java.math.BigDecimal maxWd;

	/**提币精度 表示小数点后的位数*/
	@ApiModelProperty(value = "提币精度 表示小数点后的位数", position = 9)
	private java.lang.Integer wdTickSz;

	/**过去24小时内提币额度*/
	@ApiModelProperty(value = "过去24小时内提币额度", position = 10)
	private java.math.BigDecimal wdQuota;

	/**过去24小时内已用提币额度*/
	@ApiModelProperty(value = "过去24小时内已用提币额度", position = 11)
	private java.math.BigDecimal usedWdQuota;

	/**最小提币手续费数量*/
	@ApiModelProperty(value = "最小提币手续费数量", position = 12)
	private java.math.BigDecimal minFee;

	/**最大提币手续费数量*/
	@ApiModelProperty(value = "最大提币手续费数量", position = 13)
	private java.math.BigDecimal maxFee;

	/**当前链是否为主链 如果是则返回true 否则返回false*/
	@ApiModelProperty(value = "当前链是否为主链 如果是则返回true 否则返回false", position = 14)
	private java.lang.String mainNet;

	/**币种状态 开放中live 关闭中closed*/
	@ApiModelProperty(value = "币种状态 开放中live 关闭中closed", position = 15)
	private java.lang.String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 16)
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 17)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 18)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 19)
	private java.lang.Long updateTime;
}

