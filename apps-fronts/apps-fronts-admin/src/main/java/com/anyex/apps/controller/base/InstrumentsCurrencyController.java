/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.base.entity.InstrumentsCurrency;
import com.anyex.apps.base.service.InstrumentsCurrencyService;

import com.anyex.apps.controller.base.req.ReqInstrumentsCurrency;
import com.anyex.apps.controller.base.req.ReqInstrumentsCurrencyPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

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

    @GetMapping(value = "/findBy")
    @RequiresPermissions("base:instrumentsCurrency:data")
    @ApiOperation(value = "根据ID取平台交易产品币种信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, instrumentsCurrencyService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("base:instrumentsCurrency:operator")
    @ApiOperation(value = "保存平台交易产品币种信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqInstrumentsCurrency info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            InstrumentsCurrency entity = new InstrumentsCurrency();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                instrumentsCurrencyService.insert(entity);
            } else {
                instrumentsCurrencyService.updateByPrimaryKeySelective(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("base:instrumentsCurrency:data")
    @ApiOperation(value = "查询平台交易产品币种信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqInstrumentsCurrencyPagination pagin) throws BusinessException
    {
        InstrumentsCurrency entity = new InstrumentsCurrency();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<InstrumentsCurrency> result = instrumentsCurrencyService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("base:instrumentsCurrency:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        instrumentsCurrencyService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
