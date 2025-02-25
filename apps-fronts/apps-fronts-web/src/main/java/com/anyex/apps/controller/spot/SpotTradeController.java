/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcMarketApi;
import com.anyex.exchange.viabtc.api.ViabtcTradeApi;
import com.anyex.exchange.viabtc.req.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 现货交易SpotTrade 控制器
 * <p>File：SpotTradeController.java </p>
 * <p>Title: SpotTradeController </p>
 * <p>Description:SpotTradeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/trade")
@Api(tags = "现货交易SpotTrade")
public class SpotTradeController extends GenericController
{
    @ResponseBody
    @PostMapping(value = "/orderPutLimit")
    @ApiOperation(value = "下单限价单", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPutLimit(@Validated @RequestBody ReqTradeOrderPutLimit reqTradeOrderPutLimit) throws BusinessException
    {
        log.info("tradeOrderPutLimit reqTradeOrderPutLimit:{}", reqTradeOrderPutLimit);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPutLimit(reqTradeOrderPutLimit);
        log.info("tradeOrderPutLimit respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderPutMarket")
    @ApiOperation(value = "下单市价单", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPutMarket(@Validated @RequestBody ReqTradeOrderPutMarket reqTradeOrderPutMarket) throws BusinessException
    {
        log.info("tradeOrderPutMarket reqTradeOrderPutMarket:{}", reqTradeOrderPutMarket);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPutMarket(reqTradeOrderPutMarket);
        log.info("tradeOrderPutMarket respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderCancel")
    @ApiOperation(value = "撤单", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderCancel(@Validated @RequestBody ReqTradeOrderCancel reqTradeOrderCancel) throws BusinessException
    {
        log.info("tradeOrderCancel reqTradeOrderCancel:{}", reqTradeOrderCancel);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderCancel(reqTradeOrderCancel);
        log.info("tradeOrderCancel respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderDeals")
    @ApiOperation(value = "某订单对应成交记录列表", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderDeals(@Validated @RequestBody ReqTradeOrderDeals reqTradeOrderDeals) throws BusinessException
    {
        log.info("tradeOrderDeals reqTradeOrderDeals:{}", reqTradeOrderDeals);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderDeals(reqTradeOrderDeals);
        log.info("tradeOrderDeals respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/userDeals")
    @ApiOperation(value = "用户成交记录", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> userDeals(@Validated @RequestBody ReqMarketUserDeals reqMarketUserDeals) throws BusinessException
    {
        log.info("marketUserDeals reqMarketUserDeals:{}", reqMarketUserDeals);
        JSONObject respJsonObject = ViabtcMarketApi.marketUserDeals(reqMarketUserDeals);
        log.info("marketUserDeals respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderPending")
    @ApiOperation(value = "在途订单", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPending(@Validated @RequestBody ReqTradeOrderPending reqTradeOrderPending) throws BusinessException
    {
        log.info("tradeOrderPending reqTradeOrderPending:{}", reqTradeOrderPending);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPending(reqTradeOrderPending);
        log.info("tradeOrderPending respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderPendingDetail")
    @ApiOperation(value = "某在途订单明细", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPendingDetail(@Validated @RequestBody ReqTradeOrderPendingDetail reqTradeOrderPendingDetail) throws BusinessException
    {
        log.info("tradeOrderPendingDetail reqTradeOrderPendingDetail:{}", reqTradeOrderPendingDetail);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPendingDetail(reqTradeOrderPendingDetail);
        log.info("tradeOrderPendingDetail respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderFinished")
    @ApiOperation(value = "完成订单", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderFinished(@Validated @RequestBody ReqTradeOrderFinished reqTradeOrderFinished) throws BusinessException
    {
        log.info("tradeOrderFinished reqTradeOrderFinished:{}", reqTradeOrderFinished);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderFinished(reqTradeOrderFinished);
        log.info("tradeOrderFinished respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderFinishedDetail")
    @ApiOperation(value = "某完成订单明细", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderFinishedDetail(@Validated @RequestBody ReqTradeOrderFinishedDetail reqTradeOrderFinishedDetail) throws BusinessException
    {
        log.info("tradeOrderFinishedDetail reqTradeOrderFinished:{}", reqTradeOrderFinishedDetail);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderFinishedDetail(reqTradeOrderFinishedDetail);
        log.info("tradeOrderFinishedDetail respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }
}
