/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 平台交易产品币种信息 实体请求对象
 * <p>File：ReqInstrumentsCurrency.java</p>
 * <p>Title: ReqInstrumentsCurrency</p>
 * <p>Description:ReqInstrumentsCurrency</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "平台交易产品币种信息请求对象")
public class ReqInstrumentsCurrency extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
//	/**产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION*/
//	@NotEmpty(message = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION不可为空")
//	@ApiModelProperty(value = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION", position = 1, required = true)
//	private String instType;
//
//	/**产品ID 如BTC-USD-SWAP*/
//	@NotEmpty(message = "产品ID 如BTC-USD-SWAP不可为空")
//	@ApiModelProperty(value = "产品ID 如BTC-USD-SWAP", position = 2, required = true)
//	private String instId;

//	/**区块链(BTC、ETH、TRON、BSC)*/
//	@NotEmpty(message = "区块链(BTC、ETH、TRON、BSC)不可为空")
//	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3, required = true)
//	private String blockchain;

	/**币种中文名*/
	@NotEmpty(message = "币种中文名不可为空")
	@ApiModelProperty(value = "币种中文名", position = 4, required = true)
	private String currencyName;

//	/**资产介绍*/
//	@NotEmpty(message = "资产介绍不可为空")
//	@ApiModelProperty(value = "资产介绍", position = 5, required = true)
//	private String currencyDesc;
//
//	/**资产总量*/
//	@NotEmpty(message = "资产总量不可为空")
//	@ApiModelProperty(value = "资产总量", position = 6, required = true)
//	private String currencyNum;
//
//	/**资产合约*/
//	@NotEmpty(message = "资产合约不可为空")
//	@ApiModelProperty(value = "资产合约", position = 7, required = true)
//	private String currencyContract;
//
//	/**项目简介*/
//	@NotEmpty(message = "项目简介不可为空")
//	@ApiModelProperty(value = "项目简介", position = 8, required = true)
//	private String currencyPmDesc;
//
//	/**资产链接*/
//	@NotEmpty(message = "资产链接不可为空")
//	@ApiModelProperty(value = "资产链接", position = 9, required = true)
//	private String currencyLink;
//
//	/**项目网址*/
//	@NotEmpty(message = "项目网址不可为空")
//	@ApiModelProperty(value = "项目网址", position = 10, required = true)
//	private String currencySite;
//
//	/**区块浏览器*/
//	@NotEmpty(message = "区块浏览器不可为空")
//	@ApiModelProperty(value = "区块浏览器", position = 11, required = true)
//	private String currencyBrowser;
//
//	/**白皮书*/
//	@NotEmpty(message = "白皮书不可为空")
//	@ApiModelProperty(value = "白皮书", position = 12, required = true)
//	private String currencyWhitepaperUrl;
//
//	/**X*/
//	@NotEmpty(message = "X不可为空")
//	@ApiModelProperty(value = "X", position = 13, required = true)
//	private String currencyX;
//
//	/**Telegram*/
//	@NotEmpty(message = "Telegram不可为空")
//	@ApiModelProperty(value = "Telegram", position = 14, required = true)
//	private String currencyTelegram;
//
//	/**Logo*/
//	@NotEmpty(message = "Logo不可为空")
//	@ApiModelProperty(value = "Logo", position = 15, required = true)
//	private String currencyLogoUrl;
//
//	/**备注*/
//	@ApiModelProperty(value = "备注", position = 16)
//	private String remark;

//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 17, required = true)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 18)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 19)
//	private java.lang.Long updateTime;
}

