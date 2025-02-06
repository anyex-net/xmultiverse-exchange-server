/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.operation.entity.MonitorAccountProfitLoss;
import com.anyex.apps.operation.service.MonitorAccountProfitLossService;

import com.anyex.apps.controller.operation.req.ReqMonitorAccountProfitLossPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户浮动盈亏监控 控制器
 * <p>File：MonitorAccountProfitLossController.java </p>
 * <p>Title: MonitorAccountProfitLossController </p>
 * <p>Description:MonitorAccountProfitLossController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/operation/monitorAccountProfitLoss")
@Api(tags = "账户浮动盈亏监控")
public class MonitorAccountProfitLossController extends GenericController
{
    @Autowired(required = false)
    private MonitorAccountProfitLossService monitorAccountProfitLossService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("operation:monitorAccountProfitLoss:data")
    @ApiOperation(value = "根据ID取账户浮动盈亏监控", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, monitorAccountProfitLossService.selectByPrimaryKey(id));
    }


    @PostMapping(value = "/data")
    @RequiresPermissions("operation:monitorAccountProfitLoss:data")
    @ApiOperation(value = "查询账户浮动盈亏监控", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqMonitorAccountProfitLossPagination pagin) throws BusinessException
    {
        MonitorAccountProfitLoss entity = new MonitorAccountProfitLoss();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<MonitorAccountProfitLoss> result = monitorAccountProfitLossService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/statistics")
    @RequiresPermissions("operation:monitorAccountProfitLoss:data")
    @ApiOperation(value = "账户浮动盈亏全平台统计", httpMethod = "POST")
    public JsonMessage statistics() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, monitorAccountProfitLossService.allAccountProfit());
    }

}
