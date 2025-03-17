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
import com.anyex.apps.spot.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.spot.service.OrderDetailService;

import com.anyex.apps.controller.spot.req.ReqOrderDetail;
import com.anyex.apps.controller.spot.req.ReqOrderDetailPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * order_detail_example 控制器
 * <p>File：OrderDetailExampleController.java </p>
 * <p>Title: OrderDetailExampleController </p>
 * <p>Description:OrderDetailExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/orderDetail")
@Api(description = "order_detail")
public class OrderDetailController extends GenericController
{
    @Autowired(required = false)
    private OrderDetailService orderDetailExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:orderDetail:data")
    @ApiOperation(value = "根据ID取order_detail_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, orderDetailExampleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:orderDetail:data")
    @ApiOperation(value = "查询order_detail", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqOrderDetailPagination pagin,String tableName) throws BusinessException
    {
        OrderDetail entity = new OrderDetail();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<OrderDetail> result = orderDetailExampleService.selectList(pagin,entity,tableName);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
