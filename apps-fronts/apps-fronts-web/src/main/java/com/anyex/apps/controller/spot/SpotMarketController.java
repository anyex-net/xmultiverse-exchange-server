/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
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
 * 现货市场SpotMarket 控制器
 * <p>File：SpotMarketController.java </p>
 * <p>Title: SpotMarketController </p>
 * <p>Description:SpotMarketController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/market")
@Api(tags = "现货市场SpotMarket")
public class SpotMarketController extends GenericController
{
    @ResponseBody
    @GetMapping(value = "/orderBook")
    @ApiOperation(value = "orderBook", httpMethod = "GET")
    public JsonMessage<JSONObject> orderBook(@Validated @RequestBody ReqTradeOrderBook reqTradeOrderBook) throws BusinessException
    {
        log.info("tradeOrderBook reqTradeOrderBook:{}", reqTradeOrderBook);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderBook(reqTradeOrderBook);
        log.info("tradeOrderBook respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/orderDepth")
    @ApiOperation(value = "orderDepth", httpMethod = "GET")
    public JsonMessage<JSONObject> orderDepth(@Validated @RequestBody ReqTradeOrderDepth reqTradeOrderDepth) throws BusinessException
    {
        log.info("tradeOrderDepth reqTradeOrderDepth:{}", reqTradeOrderDepth);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderDepth(reqTradeOrderDepth);
        log.info("tradeOrderDepth respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketDeals")
    @ApiOperation(value = "marketDeals", httpMethod = "GET")
    public JsonMessage<JSONObject> marketDeals(@Validated @RequestBody ReqMarketDeals reqMarketDeals) throws BusinessException
    {
        log.info("marketDeals reqMarketDeals:{}", reqMarketDeals);
        JSONObject respJsonObject = ViabtcMarketApi.marketDeals(reqMarketDeals);
        log.info("marketDeals respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketKline")
    @ApiOperation(value = "marketKline", httpMethod = "GET")
    public JsonMessage<JSONObject> marketKline(@Validated @RequestBody ReqMarketKline reqMarketKline) throws BusinessException
    {
        log.info("marketKline reqMarketKline:{}", reqMarketKline);
        JSONObject respJsonObject = ViabtcMarketApi.marketKline(reqMarketKline);
        log.info("marketKline respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketStatus")
    @ApiOperation(value = "marketStatus", httpMethod = "GET")
    public JsonMessage<JSONObject> marketStatus(@Validated @RequestBody ReqMarketStatus reqMarketStatus) throws BusinessException
    {
        log.info("marketStatus reqMarketStatus:{}", reqMarketStatus);
        JSONObject respJsonObject = ViabtcMarketApi.marketStatus(reqMarketStatus);
        log.info("marketStatus respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketStatusToday")
    @ApiOperation(value = "marketStatusToday", httpMethod = "GET")
    public JsonMessage<JSONObject> marketStatusToday(@Validated @RequestBody ReqMarketStatusToday reqMarketStatusToday) throws BusinessException
    {
        log.info("marketStatusToday reqMarketStatusToday:{}", reqMarketStatusToday);
        JSONObject respJsonObject = ViabtcMarketApi.marketStatusToday(reqMarketStatusToday);
        log.info("marketStatusToday respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketSummary")
    @ApiOperation(value = "marketSummary", httpMethod = "GET")
    public JsonMessage<JSONObject> marketSummary(@Validated @RequestBody ReqMarketSummary reqMarketSummary) throws BusinessException
    {
        log.info("marketSummary reqMarketSummary:{}", reqMarketSummary);
        JSONObject respJsonObject = ViabtcMarketApi.marketSummary(reqMarketSummary);
        log.info("marketSummary respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketList")
    @ApiOperation(value = "marketList", httpMethod = "GET")
    public JsonMessage<JSONObject> marketList() throws BusinessException
    {
        JSONObject respJsonObject = ViabtcMarketApi.marketList();
        log.info("marketList respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @GetMapping(value = "/marketLast")
    @ApiOperation(value = "marketLast", httpMethod = "GET")
    public JsonMessage<JSONObject> marketLast(ReqMarketLast reqMarketLast) throws BusinessException
    {
        log.info("marketLast reqMarketLast:{}", reqMarketLast);
        JSONObject respJsonObject = ViabtcMarketApi.marketLast(reqMarketLast);
        log.info("marketLast respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }
}
