/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台交易产品 实体请求对象
 * <p>File：ReqInstruments.java</p>
 * <p>Title: ReqInstruments</p>
 * <p>Description:ReqInstruments</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "平台交易产品请求对象")
public class ReqInstruments extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION*/
	@NotEmpty(message = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION不可为空")
	@ApiModelProperty(value = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION", position = 1, required = true)
	private java.lang.String instType;

	/**产品ID 如BTC-USD-SWAP*/
	@NotEmpty(message = "产品ID 如BTC-USD-SWAP不可为空")
	@ApiModelProperty(value = "产品ID 如BTC-USD-SWAP", position = 2, required = true)
	private java.lang.String instId;

	/**标的指数 仅适用于instType为交割/永续/期权 如BTC-USD*/
	@ApiModelProperty(value = "标的指数 仅适用于instType为交割/永续/期权 如BTC-USD", position = 3)
	private java.lang.String uly;

	/**手续费档位 每个交易产品属于哪个档位手续费*/
	@NotEmpty(message = "手续费档位 每个交易产品属于哪个档位手续费不可为空")
	@ApiModelProperty(value = "手续费档位 每个交易产品属于哪个档位手续费", position = 4, required = true)
	private java.lang.String category;

	/**交易货币币种 如BTC-USDT中的BTC 仅适用于币币*/
	@NotEmpty(message = "交易货币币种 如BTC-USDT中的BTC 仅适用于币币不可为空")
	@ApiModelProperty(value = "交易货币币种 如BTC-USDT中的BTC 仅适用于币币", position = 5, required = true)
	private java.lang.String baseCcy;

	/**计价货币币种 如BTC-USDT中的USDT 仅适用于币币*/
	@NotEmpty(message = "计价货币币种 如BTC-USDT中的USDT 仅适用于币币不可为空")
	@ApiModelProperty(value = "计价货币币种 如BTC-USDT中的USDT 仅适用于币币", position = 6, required = true)
	private java.lang.String quoteCcy;

	/**盈亏结算和保证金币种 如BTC 仅适用于交割/永续/期权*/
	@NotEmpty(message = "盈亏结算和保证金币种 如BTC 仅适用于交割/永续/期权不可为空")
	@ApiModelProperty(value = "盈亏结算和保证金币种 如BTC 仅适用于交割/永续/期权", position = 7, required = true)
	private java.lang.String settleCcy;

	/**合约面值 仅适用于交割/永续/期权*/
	@NotNull(message = "合约面值 仅适用于交割/永续/期权不可为空")
	@ApiModelProperty(value = "合约面值 仅适用于交割/永续/期权", position = 8, required = true)
	private java.math.BigDecimal ctVal;

	/**合约乘数 仅适用于交割/永续/期权*/
	@NotNull(message = "合约乘数 仅适用于交割/永续/期权不可为空")
	@ApiModelProperty(value = "合约乘数 仅适用于交割/永续/期权", position = 9, required = true)
	private java.lang.Integer ctMult;

	/**合约面值计价币种 仅适用于交割/永续/期权*/
	@NotEmpty(message = "合约面值计价币种 仅适用于交割/永续/期权不可为空")
	@ApiModelProperty(value = "合约面值计价币种 仅适用于交割/永续/期权", position = 10, required = true)
	private java.lang.String ctValCcy;

	/**期权类型 C或P 仅适用于期权*/
	@NotEmpty(message = "期权类型 C或P 仅适用于期权不可为空")
	@ApiModelProperty(value = "期权类型 C或P 仅适用于期权", position = 11, required = true)
	private java.lang.String optType;

	/**行权价格 仅适用于期权*/
	@NotNull(message = "行权价格 仅适用于期权不可为空")
	@ApiModelProperty(value = "行权价格 仅适用于期权", position = 12, required = true)
	private java.math.BigDecimal stk;

	/**上线日期 Unix时间戳的毫秒数格式 如1597026383085*/
	@NotNull(message = "上线日期 Unix时间戳的毫秒数格式 如1597026383085不可为空")
	@ApiModelProperty(value = "上线日期 Unix时间戳的毫秒数格式 如1597026383085", position = 13, required = true)
	private java.lang.Long listTime;

	/**交割/行权日期 仅适用于交割和期权 Unix时间戳的毫秒数格式 如1597026383085*/
	@NotNull(message = "交割/行权日期 仅适用于交割和期权 Unix时间戳的毫秒数格式 如1597026383085不可为空")
	@ApiModelProperty(value = "交割/行权日期 仅适用于交割和期权 Unix时间戳的毫秒数格式 如1597026383085", position = 14, required = true)
	private java.lang.Long expTime;

	/**该instId支持的最大杠杆倍数 不适用于币币、期权*/
	@NotNull(message = "该instId支持的最大杠杆倍数 不适用于币币、期权不可为空")
	@ApiModelProperty(value = "该instId支持的最大杠杆倍数 不适用于币币、期权", position = 15, required = true)
	private java.lang.Integer lever;

	/**下单价格精度 如0.0001*/
	@NotNull(message = "下单价格精度 如0.0001不可为空")
	@ApiModelProperty(value = "下单价格精度 如0.0001", position = 16, required = true)
	private java.math.BigDecimal tickSz;

	/**下单数量精度 如BTC-USDT-SWAP 1*/
	@NotNull(message = "下单数量精度 如BTC-USDT-SWAP 1不可为空")
	@ApiModelProperty(value = "下单数量精度 如BTC-USDT-SWAP 1", position = 17, required = true)
	private java.math.BigDecimal lotSz;

	/**最小下单数量*/
	@NotNull(message = "最小下单数量不可为空")
	@ApiModelProperty(value = "最小下单数量", position = 18, required = true)
	private java.math.BigDecimal minSz;

	/**正向合约linear 反向合约inverse 仅适用于交割/永续*/
	@NotEmpty(message = "正向合约linear 反向合约inverse 仅适用于交割/永续不可为空")
	@ApiModelProperty(value = "正向合约linear 反向合约inverse 仅适用于交割/永续", position = 19, required = true)
	private java.lang.String ctType;

	/**合约日期别名 本周this_week 次周next_week 季度quarter 次季度next_quarter 仅适用于交割*/
	@NotEmpty(message = "合约日期别名 本周this_week 次周next_week 季度quarter 次季度next_quarter 仅适用于交割不可为空")
	@ApiModelProperty(value = "合约日期别名 本周this_week 次周next_week 季度quarter 次季度next_quarter 仅适用于交割", position = 20, required = true)
	private java.lang.String alias;

	/**产品状态 关闭中closed 交易中live 暂停中suspend 预上线preopen 资金费结算settlement*/
	@NotEmpty(message = "产品状态 关闭中closed 交易中live 暂停中suspend 预上线preopen 资金费结算settlement不可为空")
	@ApiModelProperty(value = "产品状态 关闭中closed 交易中live 暂停中suspend 预上线preopen 资金费结算settlement", position = 21, required = true)
	private java.lang.String state;

	/**合约或现货限价单的单笔最大委托数量*/
	@NotNull(message = "合约或现货限价单的单笔最大委托数量不可为空")
	@ApiModelProperty(value = "合约或现货限价单的单笔最大委托数量", position = 22, required = true)
	private java.lang.Integer maxLmtSz;

	/**合约或现货市价单的单笔最大委托数量*/
	@NotNull(message = "合约或现货市价单的单笔最大委托数量不可为空")
	@ApiModelProperty(value = "合约或现货市价单的单笔最大委托数量", position = 23, required = true)
	private java.lang.Integer maxMktSz;

	/**合约或现货时间加权单的单笔最大委托数量*/
	@NotNull(message = "合约或现货时间加权单的单笔最大委托数量不可为空")
	@ApiModelProperty(value = "合约或现货时间加权单的单笔最大委托数量", position = 24, required = true)
	private java.lang.Integer maxTwapSz;

	/**合约或现货冰山委托的单笔最大委托数量*/
	@NotNull(message = "合约或现货冰山委托的单笔最大委托数量不可为空")
	@ApiModelProperty(value = "合约或现货冰山委托的单笔最大委托数量", position = 25, required = true)
	private java.lang.Integer maxIcebergSz;

	/**合约或现货计划委托委托的单笔最大委托数量*/
	@NotNull(message = "合约或现货计划委托委托的单笔最大委托数量不可为空")
	@ApiModelProperty(value = "合约或现货计划委托委托的单笔最大委托数量", position = 26, required = true)
	private java.lang.Integer maxTriggerSz;

	/**合约或现货止盈止损委托的单笔最大委托数量*/
	@NotNull(message = "合约或现货止盈止损委托的单笔最大委托数量不可为空")
	@ApiModelProperty(value = "合约或现货止盈止损委托的单笔最大委托数量", position = 27, required = true)
	private java.lang.Integer maxStopSz;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 28)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 29, required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 30)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 31)
	private java.lang.Long updateTime;
}

