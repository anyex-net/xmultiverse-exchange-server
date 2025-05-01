/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.alibaba.fastjson.JSONArray;
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

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping(value = "/marketList")
    @ApiOperation(value = "所有交易对列表marketList", httpMethod = "POST")
    public JsonMessage<List<JSONObject>> marketList() throws BusinessException
    {
        List<JSONObject> listJSONObject = new ArrayList<JSONObject>();
        //
        JSONObject marketListJsonObject = ViabtcMarketApi.marketList();
        log.info("marketList marketListJsonObject:{}", marketListJsonObject);
        if(null != marketListJsonObject && marketListJsonObject.size() > 0)
        {
            JSONArray marketListJsonObjectArray = marketListJsonObject.getJSONArray("result");
            //
            for(int i=0; i<marketListJsonObjectArray.size(); i++)
            {
                //
                JSONObject itemJsonObject = marketListJsonObjectArray.getJSONObject(i);
                itemJsonObject.put("tradepair", itemJsonObject.getString("stock") + "/" + itemJsonObject.getString("money"));
                log.info("marketList itemJsonObject:{}", itemJsonObject);
                //
                listJSONObject.add(itemJsonObject);
            }
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS, listJSONObject);
    }

    @ResponseBody
    @PostMapping(value = "/marketListStatusToday")
    @ApiOperation(value = "所有交易对列表今日市场行情", httpMethod = "POST")
    public JsonMessage<List<JSONObject>> marketListStatusToday() throws BusinessException
    {
        //
        return getJsonMessage(CommonEnums.SUCCESS, ViabtcMarketApi.marketListStatusToday());
    }

    @ResponseBody
    @PostMapping(value = "/marketLast")
    @ApiOperation(value = "某交易对-最新价marketLast", httpMethod = "POST")
    public JsonMessage<JSONObject> marketLast(@Validated @RequestBody ReqMarketLast reqMarketLast) throws BusinessException
    {
        log.info("marketLast reqMarketLast:{}", reqMarketLast);
        JSONObject respJsonObject = ViabtcMarketApi.marketLast(reqMarketLast);
        log.info("marketLast respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderBook")
    @ApiOperation(value = "某交易对-方向深度orderBook", httpMethod = "POST")
    public JsonMessage<JSONObject> orderBook(@Validated @RequestBody ReqTradeOrderBook reqTradeOrderBook) throws BusinessException
    {
        log.info("tradeOrderBook reqTradeOrderBook:{}", reqTradeOrderBook);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderBook(reqTradeOrderBook);
        log.info("tradeOrderBook respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/orderDepth")
    @ApiOperation(value = "某交易对-深度orderDepth", httpMethod = "POST")
    public JsonMessage<JSONObject> orderDepth(@Validated @RequestBody ReqTradeOrderDepth reqTradeOrderDepth) throws BusinessException
    {
        log.info("tradeOrderDepth reqTradeOrderDepth:{}", reqTradeOrderDepth);
        JSONObject respJsonObject = ViabtcTradeApi.tradeOrderDepth(reqTradeOrderDepth);
        log.info("tradeOrderDepth respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/marketDeals")
    @ApiOperation(value = "某交易对-成交marketDeals", httpMethod = "POST")
    public JsonMessage<JSONObject> marketDeals(@Validated @RequestBody ReqMarketDeals reqMarketDeals) throws BusinessException
    {
        log.info("marketDeals reqMarketDeals:{}", reqMarketDeals);
        JSONObject respJsonObject = ViabtcMarketApi.marketDeals(reqMarketDeals);
        log.info("marketDeals respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/marketKline")
    @ApiOperation(value = "某交易对-K线marketKline", httpMethod = "POST")
    public JsonMessage<JSONObject> marketKline(@Validated @RequestBody ReqMarketKline reqMarketKline) throws BusinessException
    {
        log.info("marketKline reqMarketKline:{}", reqMarketKline);
        JSONObject respJsonObject = ViabtcMarketApi.marketKline(reqMarketKline);
        log.info("marketKline respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/marketStatus")
    @ApiOperation(value = "某交易对-市场行情marketStatus", httpMethod = "POST")
    public JsonMessage<JSONObject> marketStatus(@Validated @RequestBody ReqMarketStatus reqMarketStatus) throws BusinessException
    {
        log.info("marketStatus reqMarketStatus:{}", reqMarketStatus);
        JSONObject respJsonObject = ViabtcMarketApi.marketStatus(reqMarketStatus);
        log.info("marketStatus respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/marketStatusToday")
    @ApiOperation(value = "某交易对-今日市场行情marketStatusToday", httpMethod = "POST")
    public JsonMessage<JSONObject> marketStatusToday(@Validated @RequestBody ReqMarketStatusToday reqMarketStatusToday) throws BusinessException
    {
        log.info("marketStatusToday reqMarketStatusToday:{}", reqMarketStatusToday);
        JSONObject respJsonObject = ViabtcMarketApi.marketStatusToday(reqMarketStatusToday);
        log.info("marketStatusToday respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @ResponseBody
    @PostMapping(value = "/marketSummary")
    @ApiOperation(value = "某交易对-行情概览marketSummary", httpMethod = "POST")
    public JsonMessage<JSONObject> marketSummary(@Validated @RequestBody ReqMarketSummary reqMarketSummary) throws BusinessException
    {
        log.info("marketSummary reqMarketSummary:{}", reqMarketSummary);
        JSONObject respJsonObject = ViabtcMarketApi.marketSummary(reqMarketSummary);
        log.info("marketSummary respJsonObject:{}", respJsonObject);
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }
}
