/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.base.entity.Currencies;
import com.anyex.apps.base.service.CurrenciesService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.base.req.ReqCurrencies;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台币种 控制器
 * <p>File：CurrenciesController.java </p>
 * <p>Title: CurrenciesController </p>
 * <p>Description:CurrenciesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/currencies")
@Api(tags = "平台币种")
public class CurrenciesController extends GenericController
{
    @Autowired(required = false)
    private CurrenciesService currenciesService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询平台币种所有列表", httpMethod = "POST")
    public JsonMessage<List<Currencies>> data() throws BusinessException
    {
        Currencies currencies = new Currencies();
        currencies.setState("live"); // 开放中live
        List<Currencies> result = currenciesService.findList(currencies);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findByCurrency")
    @ApiOperation(value = "根据币种currency取平台币种列表", httpMethod = "GET")
    public JsonMessage<List<Currencies>> findByCurrency(@Validated @RequestBody ReqCurrencies reqCurrencies) throws BusinessException
    {
        Currencies currencies = new Currencies();
        currencies.setState("live"); // 开放中live
        currencies.setCurrency(reqCurrencies.getCurrency());
        List<Currencies> result = currenciesService.findList(currencies);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
