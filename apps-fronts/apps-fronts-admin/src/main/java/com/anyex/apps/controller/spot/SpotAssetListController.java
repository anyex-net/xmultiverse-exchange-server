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
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 现货资产币种
 */
@Slf4j
@RestController
@RequestMapping("/spot/spotAssetList")
@Api(tags = "现货资产币种")
public class SpotAssetListController extends GenericController {

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:spotAssetList:data")
    @ApiOperation(value = "现货资产币种", httpMethod = "POST")
    public JsonMessage<JSONObject> data() throws BusinessException {
        JSONObject jsonObject = ViabtcAssetApi.assetList();
        return new JsonMessage<>(CommonEnums.SUCCESS,jsonObject);
    }
}