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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
@RequestMapping("/spot/sliceBalance")
@Api(description = "slice_balance_example")
public class SliceBalanceController extends GenericController
{
    @Autowired(required = false)
    private SliceBalanceService sliceBalanceExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:sliceBalance:data")
    @ApiOperation(value = "根据ID取slice_balance_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, sliceBalanceExampleService.selectByPrimaryKey(id));
    }


    @PostMapping(value = "/data")
    @RequiresPermissions("spot:sliceBalance:data")
    @ApiOperation(value = "查询", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSliceBalancePagination pagin,String date) throws BusinessException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        long timestamp = zonedDateTime.toInstant().toEpochMilli() / 1000;
        String tableName = "slice_balance_"+timestamp;
        SliceBalance entity = new SliceBalance();
        BeanUtils.copyProperties(pagin, entity);
        try {
            PaginateResult<SliceBalance> result = sliceBalanceExampleService.selectList(pagin,entity,tableName);
            return getJsonMessage(CommonEnums.SUCCESS, result);
        }catch (Exception e){
            throw new BusinessException("数据不存在，请重新选择日期");
        }
    }

}
