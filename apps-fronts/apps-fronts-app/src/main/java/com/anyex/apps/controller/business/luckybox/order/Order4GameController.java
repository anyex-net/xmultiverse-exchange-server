/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.order;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.order.req.ReqOrder4GamePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import com.anyex.apps.business.luckybox.order.service.Order4GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 游戏订单记录 控制器
 * <p>File：Order4GameController.java </p>
 * <p>Title: Order4GameController </p>
 * <p>Description:Order4GameController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/order/order4Game")
@Api(tags = "游戏订单记录")
public class Order4GameController extends GenericController
{
    @Autowired(required = false)
    private Order4GameService order4GameService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询游戏订单记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Order4Game>> data(@Validated @RequestBody ReqOrder4GamePagination reqOrder4GamePagination) throws BusinessException
    {
        //
        Order4Game order4Game = new Order4Game();
        BeanUtils.copyProperties(reqOrder4GamePagination, order4Game);
        log.info("order4Game:{}", order4Game);
        //
        PaginateResult<Order4Game> result = order4GameService.search(reqOrder4GamePagination, order4Game);
        result.getRecords().stream().forEach(entity->
        {
            entity.setEmail(DesensitizedUtil.email(entity.getEmail())); // 邮箱模糊
        });
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
