/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.order;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.order.req.ReqOrder4GamePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import com.anyex.apps.business.luckybox.order.service.Order4GameService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 游戏订单记录表 控制器
 * <p>File：Order4GameController.java </p>
 * <p>Title: Order4GameController </p>
 * <p>Description:Order4GameController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/business/luckybox/order/order4Game")
@Api(tags = "游戏订单记录")
public class Order4GameController extends GenericController
{
    @Autowired(required = false)
    private Order4GameService order4GameService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("order:order4Game:data")
    @ApiOperation(value = "根据ID取游戏订单记录", httpMethod = "GET")
    public JsonMessage<Order4Game> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, order4GameService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("order:order4Game:data")
    @ApiOperation(value = "查询游戏订单记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Order4Game>> data(@Validated @ModelAttribute ReqOrder4GamePagination reqOrder4GamePagination) throws BusinessException
    {
        //
        Order4Game order4Game = new Order4Game();
        BeanUtils.copyProperties(reqOrder4GamePagination, order4Game);
        //
        PaginateResult<Order4Game> result = order4GameService.search(reqOrder4GamePagination, order4Game);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
