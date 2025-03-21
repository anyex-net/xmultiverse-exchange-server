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

import com.anyex.apps.spot.entity.SliceOrder;
import com.anyex.apps.spot.service.SliceOrderService;

import com.anyex.apps.controller.spot.req.ReqSliceOrder;
import com.anyex.apps.controller.spot.req.ReqSliceOrderPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * slice_order_example 控制器
 * <p>File：SliceOrderExampleController.java </p>
 * <p>Title: SliceOrderExampleController </p>
 * <p>Description:SliceOrderExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/sliceOrder")
@Api(description = "slice_order_example")
public class SliceOrderController extends GenericController
{
    @Autowired(required = false)
    private SliceOrderService sliceOrderExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:sliceOrder:data")
    @ApiOperation(value = "根据ID取slice_order_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, sliceOrderExampleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:sliceOrder:data")
    @ApiOperation(value = "查询slice_order_example", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSliceOrderPagination pagin,String date) throws BusinessException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        long timestamp = zonedDateTime.toInstant().toEpochMilli() / 1000;
        String tableName = "slice_order_"+timestamp;
        SliceOrder entity = new SliceOrder();
        BeanUtils.copyProperties(pagin, entity);
        try{
            PaginateResult<SliceOrder> result = sliceOrderExampleService.selectList(pagin,entity,tableName);
            return getJsonMessage(CommonEnums.SUCCESS, result);
        }catch (Exception e){
            throw new BusinessException("数据不存在，请重新选择日期");
        }

    }
}
