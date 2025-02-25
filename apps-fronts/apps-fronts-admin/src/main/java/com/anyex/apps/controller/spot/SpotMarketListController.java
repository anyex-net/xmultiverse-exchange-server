package com.anyex.apps.controller.spot;
/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.exchange.viabtc.api.ViabtcMarketApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spot/spotMarketList")
@Api(tags = "现货市场币对")
public class SpotMarketListController extends GenericController {

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:spotMarketList:data")
    @ApiOperation(value = "现货市场币对", httpMethod = "POST")
    public JsonMessage<JSONObject> data() throws BusinessException {
        JSONObject jsonObject = ViabtcMarketApi.marketList();
        return new JsonMessage<>(CommonEnums.SUCCESS,jsonObject);
    }
}