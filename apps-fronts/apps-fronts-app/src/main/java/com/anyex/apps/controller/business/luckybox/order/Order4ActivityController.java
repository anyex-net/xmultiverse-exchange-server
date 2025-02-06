/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.order;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.order.req.ReqOrder4ActivityPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import com.anyex.apps.business.luckybox.order.service.Order4ActivityService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动订单记录 控制器
 * <p>File：Order4ActivityController.java </p>
 * <p>Title: Order4ActivityController </p>
 * <p>Description:Order4ActivityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/order/order4Activity")
@Api(tags = "活动订单记录")
public class Order4ActivityController extends GenericController
{
    @Autowired(required = false)
    private Order4ActivityService order4ActivityService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询活动订单记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Order4Activity>> data(@Validated @RequestBody ReqOrder4ActivityPagination reqOrder4ActivityPagination) throws BusinessException
    {
        //
        Order4Activity order4Activity = new Order4Activity();
        BeanUtils.copyProperties(reqOrder4ActivityPagination, order4Activity);
        //
        log.info("data order4Activity:{}", order4Activity);
        PaginateResult<Order4Activity> result = order4ActivityService.search(reqOrder4ActivityPagination, order4Activity);
        result.getRecords().stream().forEach(entity->
        {
            entity.setEmail(DesensitizedUtil.email(entity.getEmail())); // 邮箱模糊
        });
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/myOrder4ActivityData")
    @ApiOperation(value = "查询我的活动订单记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Order4Activity>> myOrder4ActivityData(@Validated @RequestBody ReqOrder4ActivityPagination reqOrder4ActivityPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        Order4Activity order4Activity = new Order4Activity();
        BeanUtils.copyProperties(reqOrder4ActivityPagination, order4Activity);
        order4Activity.setAccountId(principal.getId());
        //
        log.info("myOrder4ActivityData order4Activity:{}", order4Activity);
        PaginateResult<Order4Activity> result = order4ActivityService.search(reqOrder4ActivityPagination, order4Activity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取活动订单记录", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "订单Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<Order4Activity> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        Order4Activity order4ActivityDB = order4ActivityService.selectByPrimaryKey(id);
        //
        Order4Activity order4ActivitySearch = new Order4Activity();
        order4ActivitySearch.setActivityId(order4ActivityDB.getActivityId());
        order4ActivitySearch.setActivityCurrentRound(order4ActivityDB.getActivityCurrentRound());
        order4ActivitySearch.setIsLotteryDrawn(true);
        order4ActivitySearch.setIsWinning(true);
        log.info("order4ActivitySearch:{}", order4ActivitySearch);
        List<Order4Activity> listOrder4Activity = order4ActivityService.findList(order4ActivityDB);
        if(null != listOrder4Activity && listOrder4Activity.size() > 0){
            order4ActivityDB.setWinningAccountEmail(DesensitizedUtil.email(listOrder4Activity.get(0).getEmail()));
        }
        //
        log.info("order4ActivityDB:{}", order4ActivityDB);
        return this.getJsonMessage(CommonEnums.SUCCESS, order4ActivityDB);
    }
}
