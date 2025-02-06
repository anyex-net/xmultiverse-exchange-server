/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.order;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.order.req.ReqOrder4ActivityPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.business.luckybox.order.service.Order4ActivityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.order.entity.Order4Activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 活动订单记录表 控制器
 * <p>File：Order4ActivityController.java </p>
 * <p>Title: Order4ActivityController </p>
 * <p>Description:Order4ActivityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/business/luckybox/order/order4Activity")
@Api(tags = "活动订单记录")
public class Order4ActivityController extends GenericController
{
    @Autowired(required = false)
    private Order4ActivityService order4ActivityService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("order:order4Activity:data")
    @ApiOperation(value = "根据ID取活动订单记录", httpMethod = "GET")
    public JsonMessage<Order4Activity> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, order4ActivityService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("order:order4Activity:data")
    @ApiOperation(value = "查询活动订单记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Order4Activity>> data(@Validated @ModelAttribute ReqOrder4ActivityPagination reqOrder4ActivityPagination) throws BusinessException
    {
        //
        Order4Activity order4Activity = new Order4Activity();
        BeanUtils.copyProperties(reqOrder4ActivityPagination, order4Activity);
        //
        PaginateResult<Order4Activity> result = order4ActivityService.search(reqOrder4ActivityPagination, order4Activity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
