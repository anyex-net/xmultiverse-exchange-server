/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqSpotAssetBalanceHistory;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 现货资产账户变动历史SpotAssetBalancesHistory 控制器
 * <p>File：SpotAssetBalancesHistoryController.java </p>
 * <p>Title: SpotAssetBalancesHistoryController </p>
 * <p>Description:SpotAssetBalancesHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/spotAssetBalances")
@Api(tags = "现货账户资产变动历史")
public class SpotAssetBalancesHistoryController extends GenericController
{
    @ResponseBody
    @PostMapping(value = "/assetBalanceHistory")
    @ApiOperation(value = "现货资产变动历史", httpMethod = "POST")
    public JsonMessage<JSONObject> assetBalanceHistory(@Validated @RequestBody ReqSpotAssetBalanceHistory reqSpotAssetBalanceHistory) throws BusinessException
    {
        log.info("assetBalanceHistory reqSpotAssetBalanceHistory:{}", reqSpotAssetBalanceHistory);
        ReqAssetBalanceHistory reqAssetBalanceHistory = new ReqAssetBalanceHistory();
        BeanUtils.copyProperties(reqSpotAssetBalanceHistory, reqAssetBalanceHistory);
        reqAssetBalanceHistory.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcAssetApi.balanceHistory(reqAssetBalanceHistory);
        log.info("assetBalanceHistory respJsonObject:{}", respJsonObject);
        //
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }
}
