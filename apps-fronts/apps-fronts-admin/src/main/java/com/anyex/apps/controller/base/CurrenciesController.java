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

import com.anyex.apps.base.entity.Currencies;
import com.anyex.apps.base.service.CurrenciesService;

import com.anyex.apps.controller.base.req.ReqCurrencies;
import com.anyex.apps.controller.base.req.ReqCurrenciesPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

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

    @GetMapping(value = "/findBy")
    @RequiresPermissions("base:currencies:data")
    @ApiOperation(value = "根据ID取平台币种", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, currenciesService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("base:currencies:operator")
    @ApiOperation(value = "保存平台币种", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqCurrencies info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            Currencies entity = new Currencies();
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
                currenciesService.insert(entity);
            } else {
                currenciesService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("base:currencies:data")
    @ApiOperation(value = "查询平台币种", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqCurrenciesPagination pagin) throws BusinessException
    {
        Currencies entity = new Currencies();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<Currencies> result = currenciesService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("base:currencies:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        currenciesService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
