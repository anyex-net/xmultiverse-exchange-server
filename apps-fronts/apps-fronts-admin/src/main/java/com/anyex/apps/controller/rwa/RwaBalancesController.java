/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.service.RwaBalancesService;

import com.anyex.apps.controller.rwa.req.ReqRwaBalances;
import com.anyex.apps.controller.rwa.req.ReqRwaBalancesPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA账户余额 控制器
 * <p>File：RwaBalancesController.java </p>
 * <p>Title: RwaBalancesController </p>
 * <p>Description:RwaBalancesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaBalances")
@Api(tags = "RWA账户余额")
public class RwaBalancesController extends GenericController
{
    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaBalances:data")
    @ApiOperation(value = "根据ID取RWA账户余额", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaBalancesService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("rwa:rwaBalances:operator")
    @ApiOperation(value = "保存RWA账户余额", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqRwaBalances info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            RwaBalances entity = new RwaBalances();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
//                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                rwaBalancesService.insert(entity);
            } else {
                rwaBalancesService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaBalances:data")
    @ApiOperation(value = "查询RWA账户余额", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRwaBalancesPagination pagin) throws BusinessException
    {
        RwaBalances entity = new RwaBalances();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaBalances> result = rwaBalancesService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("rwa:rwaBalances:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        rwaBalancesService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
