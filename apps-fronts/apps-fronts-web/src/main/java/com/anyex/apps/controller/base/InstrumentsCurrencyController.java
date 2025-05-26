/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.base.entity.InstrumentsCurrency;
import com.anyex.apps.base.service.InstrumentsCurrencyService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.base.req.ReqInstrumentsCurrency;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台交易产品币种信息 控制器
 * <p>File：InstrumentsCurrencyController.java </p>
 * <p>Title: InstrumentsCurrencyController </p>
 * <p>Description:InstrumentsCurrencyController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/instrumentsCurrency")
@Api(tags = "平台交易产品币种信息")
public class InstrumentsCurrencyController extends GenericController
{
    @Autowired(required = false)
    private InstrumentsCurrencyService instrumentsCurrencyService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询平台交易产品币种信息", httpMethod = "POST")
    public JsonMessage<List<InstrumentsCurrency>> data(@Validated @RequestBody ReqInstrumentsCurrency reqInstrumentsCurrency) throws BusinessException
    {
        InstrumentsCurrency entity = new InstrumentsCurrency();
        BeanUtils.copyProperties(reqInstrumentsCurrency, entity);
        List<InstrumentsCurrency> result = instrumentsCurrencyService.findList(entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    @GetMapping(value = "/findBy")
//    @ApiOperation(value = "根据ID取平台交易产品币种信息", httpMethod = "GET")
//    public JsonMessage<InstrumentsCurrency> findBy(Long id) throws BusinessException
//    {
//        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        return this.getJsonMessage(CommonEnums.SUCCESS, instrumentsCurrencyService.selectByPrimaryKey(id));
//    }
}
