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

import com.anyex.apps.spot.entity.BalanceHistory;
import com.anyex.apps.spot.service.BalanceHistoryService;

import com.anyex.apps.controller.spot.req.ReqBalanceHistory;
import com.anyex.apps.controller.spot.req.ReqBalanceHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * balance_history_example 控制器
 * <p>File：BalanceHistoryExampleController.java </p>
 * <p>Title: BalanceHistoryExampleController </p>
 * <p>Description:BalanceHistoryExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/balanceHistory")
@Api(description = "balance_history")
public class BalanceHistoryController extends GenericController
{
    @Autowired(required = false)
    private BalanceHistoryService balanceHistoryExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:balanceHistoryExample:data")
    @ApiOperation(value = "根据ID取balance_history_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, balanceHistoryExampleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:balanceHistory:data")
    @ApiOperation(value = "查询balance_history", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqBalanceHistoryPagination pagin,String tableName) throws BusinessException
    {
        BalanceHistory entity = new BalanceHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<BalanceHistory> result = balanceHistoryExampleService.selectList(pagin,entity,tableName);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

}
