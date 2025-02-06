/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset.resp;

import com.anyex.apps.asset.entity.WalletAssetFlows;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "钱包资产返回对象")
public class RespWalletAssetFlows extends WalletAssetFlows
{
	/**转账编号*/
	@ApiModelProperty(value = "转账编号")
	private String trxNo;

	/**转账渠道*/
	@ApiModelProperty(value = "转账渠道")
	private String trxChannel;
}

