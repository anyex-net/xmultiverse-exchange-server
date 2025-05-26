/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台交易产品币种信息 分页请求对象
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
@ApiModel(description = "平台交易产品币种信息分页请求对象")
public class ReqInstrumentsCurrencyPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION*/
	@ApiModelProperty(value = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION", position = 1)
	private String instType;

	/**产品ID 如BTC-USD-SWAP*/
	@ApiModelProperty(value = "产品ID 如BTC-USD-SWAP", position = 2)
	private String instId;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3)
	private String blockchain;

	/**币种中文名*/
	@ApiModelProperty(value = "币种中文名", position = 4)
	private String currencyName;

	/**资产介绍*/
	@ApiModelProperty(value = "资产介绍", position = 5)
	private String currencyDesc;

	/**资产总量*/
	@ApiModelProperty(value = "资产总量", position = 6)
	private String currencyNum;

	/**资产合约*/
	@ApiModelProperty(value = "资产合约", position = 7)
	private String currencyContract;

	/**项目简介*/
	@ApiModelProperty(value = "项目简介", position = 8)
	private String currencyPmDesc;

	/**资产链接*/
	@ApiModelProperty(value = "资产链接", position = 9)
	private String currencyLink;

	/**项目网址*/
	@ApiModelProperty(value = "项目网址", position = 10)
	private String currencySite;

	/**区块浏览器*/
	@ApiModelProperty(value = "区块浏览器", position = 11)
	private String currencyBrowser;

	/**白皮书*/
	@ApiModelProperty(value = "白皮书", position = 12)
	private String currencyWhitepaperUrl;

	/**X*/
	@ApiModelProperty(value = "X", position = 13)
	private String currencyX;

	/**Telegram*/
	@ApiModelProperty(value = "Telegram", position = 14)
	private String currencyTelegram;

	/**Logo*/
	@ApiModelProperty(value = "Logo", position = 15)
	private String currencyLogoUrl;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 16)
	private String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 17)
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

