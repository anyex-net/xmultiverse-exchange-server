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
import com.anyex.apps.spot.entity.OrderHistory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.anyex.apps.spot.service.OrderHistoryService;

import com.anyex.apps.controller.spot.req.ReqOrderHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * order_history_example 控制器
 * <p>File：OrderHistoryExampleController.java </p>
 * <p>Title: OrderHistoryExampleController </p>
 * <p>Description:OrderHistoryExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/orderHistory")
@Api(description = "order_history_example")
public class OrderHistoryController extends GenericController
{
    @Autowired(required = false)
    private OrderHistoryService orderHistoryExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:orderHistory:data")
    @ApiOperation(value = "根据ID取order_history_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, orderHistoryExampleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:orderHistory:data")
    @ApiOperation(value = "查询order_history", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqOrderHistoryPagination pagin,String tableName) throws BusinessException
    {
        OrderHistory entity = new OrderHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<OrderHistory> result = orderHistoryExampleService.selectList(pagin,entity,tableName);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
