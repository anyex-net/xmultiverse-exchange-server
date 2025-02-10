/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.fund.entity.DepositAddress;
import com.anyex.apps.fund.service.DepositAddressService;

import com.anyex.apps.controller.fund.req.ReqDepositAddress;
import com.anyex.apps.controller.fund.req.ReqDepositAddressPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 充值地址 控制器
 * <p>File：DepositAddressController.java </p>
 * <p>Title: DepositAddressController </p>
 * <p>Description:DepositAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/depositAddress")
@Api(tags = "充值地址")
public class DepositAddressController extends GenericController
{
    @Autowired(required = false)
    private DepositAddressService depositAddressService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("fund:depositAddress:data")
    @ApiOperation(value = "根据ID取充值地址", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, depositAddressService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("fund:depositAddress:operator")
    @ApiOperation(value = "保存充值地址", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqDepositAddress info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            DepositAddress entity = new DepositAddress();
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
                depositAddressService.insert(entity);
            } else {
                depositAddressService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("fund:depositAddress:data")
    @ApiOperation(value = "查询充值地址", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqDepositAddressPagination pagin) throws BusinessException
    {
        DepositAddress entity = new DepositAddress();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<DepositAddress> result = depositAddressService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("fund:depositAddress:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        depositAddressService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
