/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.spot.entity.SliceBalance;
import com.anyex.apps.spot.service.SliceBalanceService;

import com.anyex.apps.controller.spot.req.ReqSliceBalance;
import com.anyex.apps.controller.spot.req.ReqSliceBalancePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * slice_balance_example 控制器
 * <p>File：SliceBalanceExampleController.java </p>
 * <p>Title: SliceBalanceExampleController </p>
 * <p>Description:SliceBalanceExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/sliceBalanceExample")
@Api(description = "slice_balance_example")
public class SliceBalanceController extends GenericController
{
    @Autowired(required = false)
    private SliceBalanceService sliceBalanceExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:sliceBalanceExample:data")
    @ApiOperation(value = "根据ID取slice_balance_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, sliceBalanceExampleService.selectByPrimaryKey(id));
    }


    @PostMapping(value = "/data")
    @RequiresPermissions("spot:sliceBalanceExample:data")
    @ApiOperation(value = "查询slice_balance_example", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSliceBalancePagination pagin) throws BusinessException
    {
        SliceBalance entity = new SliceBalance();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SliceBalance> result = sliceBalanceExampleService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("spot:sliceBalanceExample:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        sliceBalanceExampleService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
