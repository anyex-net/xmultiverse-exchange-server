/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.spot.req.*;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.exchange.viabtc.api.ViabtcMarketApi;
import com.anyex.exchange.viabtc.api.ViabtcTradeApi;
import com.anyex.exchange.viabtc.req.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    @ApiOperation(value = "下单限价单orderPutLimit", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPutLimit(@Validated @RequestBody ReqSpotTradeOrderPutLimit reqSpotTradeOrderPutLimit) throws BusinessException
    {
        log.info("orderPutLimit reqSpotTradeOrderPutLimit:{}", reqSpotTradeOrderPutLimit);
        ReqTradeOrderPutLimit reqTradeOrderPutLimit = new ReqTradeOrderPutLimit();
        BeanUtils.copyProperties(reqSpotTradeOrderPutLimit, reqTradeOrderPutLimit);
        reqTradeOrderPutLimit.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPutLimit(reqTradeOrderPutLimit);
        log.info("tradeOrderPutLimit respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderPutMarket")
    @ApiOperation(value = "下单市价单orderPutMarket", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPutMarket(@Validated @RequestBody ReqSpotTradeOrderPutMarket reqSpotTradeOrderPutMarket) throws BusinessException
    {
        log.info("orderPutMarket reqSpotTradeOrderPutMarket:{}", reqSpotTradeOrderPutMarket);
        ReqTradeOrderPutMarket reqTradeOrderPutMarket = new ReqTradeOrderPutMarket();
        BeanUtils.copyProperties(reqSpotTradeOrderPutMarket, reqTradeOrderPutMarket);
        reqTradeOrderPutMarket.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPutMarket(reqTradeOrderPutMarket);
        log.info("tradeOrderPutMarket respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderCancel")
    @ApiOperation(value = "撤单orderCancel", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderCancel(@Validated @RequestBody ReqSpotTradeOrderCancel reqSpotTradeOrderCancel) throws BusinessException
    {
        log.info("orderCancel reqSpotTradeOrderCancel:{}", reqSpotTradeOrderCancel);
        ReqTradeOrderCancel reqTradeOrderCancel = new ReqTradeOrderCancel();
        BeanUtils.copyProperties(reqSpotTradeOrderCancel, reqTradeOrderCancel);
        reqTradeOrderCancel.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderCancel(reqTradeOrderCancel);
        log.info("tradeOrderCancel respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderDeals")
    @ApiOperation(value = "某订单对应成交记录列表orderDeals", httpMethod = "POST")
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
    @ApiOperation(value = "用户成交记录userDeals", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> userDeals(@Validated @RequestBody ReqSpotMarketUserDeals reqSpotMarketUserDeals) throws BusinessException
    {
        log.info("userDeals reqSpotMarketUserDeals:{}", reqSpotMarketUserDeals);
        ReqMarketUserDeals reqMarketUserDeals = new ReqMarketUserDeals();
        BeanUtils.copyProperties(reqSpotMarketUserDeals, reqMarketUserDeals);
        reqMarketUserDeals.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcMarketApi.marketUserDeals(reqMarketUserDeals);
        log.info("marketUserDeals respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderPending")
    @ApiOperation(value = "在途订单orderPending", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderPending(@Validated @RequestBody ReqSpotTradeOrderPending reqSpotTradeOrderPending) throws BusinessException
    {
        log.info("orderPending reqSpotTradeOrderPending:{}", reqSpotTradeOrderPending);
        ReqTradeOrderPending reqTradeOrderPending = new ReqTradeOrderPending();
        BeanUtils.copyProperties(reqSpotTradeOrderPending, reqTradeOrderPending);
        reqTradeOrderPending.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderPending(reqTradeOrderPending);
        log.info("tradeOrderPending respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderPendingDetail")
    @ApiOperation(value = "某在途订单明细orderPendingDetail", httpMethod = "POST")
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
    @ApiOperation(value = "完成订单orderFinished", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderFinished(@Validated @RequestBody ReqSpotTradeOrderFinished reqSpotTradeOrderFinished) throws BusinessException
    {
        log.info("orderFinished reqSpotTradeOrderFinished:{}", reqSpotTradeOrderFinished);
        ReqTradeOrderFinished reqTradeOrderFinished = new ReqTradeOrderFinished();
        BeanUtils.copyProperties(reqSpotTradeOrderFinished, reqTradeOrderFinished);
        reqTradeOrderFinished.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderFinished(reqTradeOrderFinished);
        log.info("tradeOrderFinished respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderFinishedDetail")
    @ApiOperation(value = "某完成订单明细orderFinishedDetail", httpMethod = "POST")
    @AccessLimit(limit = 3, timeScope = 1, isLogin = true) // 登录情况下限制1秒内最多请求3次
    public JsonMessage<JSONObject> orderFinishedDetail(@Validated @RequestBody ReqTradeOrderFinishedDetail reqTradeOrderFinishedDetail) throws BusinessException
    {
        log.info("tradeOrderFinishedDetail reqTradeOrderFinished:{}", reqTradeOrderFinishedDetail);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderFinishedDetail(reqTradeOrderFinishedDetail);
        log.info("tradeOrderFinishedDetail respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }
}
