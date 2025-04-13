/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 现货资产账户余额SpotAssetBalances 控制器
 * <p>File：SpotAssetBalancesController.java </p>
 * <p>Title: SpotAssetBalancesController </p>
 * <p>Description:SpotAssetBalancesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/spotAssetBalances")
@Api(tags = "现货账户资产余额")
public class SpotAssetBalancesController extends GenericController
{
    @ResponseBody
    @PostMapping(value = "/data")
    @ApiOperation(value = "查询现货账户资产余额列表", httpMethod = "POST")
    public JsonMessage<JSONObject> data() throws BusinessException
    {
        ReqAssetBalanceQuery reqAssetBalanceQuery = new ReqAssetBalanceQuery();
        reqAssetBalanceQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        // reqAssetBalanceQuery.setCurrency("BTC");
        log.info("balanceQuery reqAssetBalanceQuery:{}", reqAssetBalanceQuery);
        JSONObject respJsonObject = ViabtcAssetApi.balanceQuery(reqAssetBalanceQuery);
        log.info("balanceQuery respJsonObject:{}", respJsonObject);
        //
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }
}
