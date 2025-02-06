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

import com.anyex.apps.operation.entity.MonitorWalletAssetFlows;
import com.anyex.apps.operation.service.MonitorWalletAssetFlowsService;

import com.anyex.apps.controller.operation.req.ReqMonitorWalletAssetFlowsPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 钱包资产流水监控 控制器
 * <p>File：MonitorWalletAssetFlowsController.java </p>
 * <p>Title: MonitorWalletAssetFlowsController </p>
 * <p>Description:MonitorWalletAssetFlowsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/operation/monitorWalletAssetFlows")
@Api(tags = "钱包资产流水监控")
public class MonitorWalletAssetFlowsController extends GenericController
{
    @Autowired(required = false)
    private MonitorWalletAssetFlowsService monitorWalletAssetFlowsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("operation:monitorWalletAssetFlows:data")
    @ApiOperation(value = "根据ID取钱包资产流水监控", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, monitorWalletAssetFlowsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("operation:monitorWalletAssetFlows:data")
    @ApiOperation(value = "查询钱包资产流水监控", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqMonitorWalletAssetFlowsPagination pagin) throws BusinessException
    {
        MonitorWalletAssetFlows entity = new MonitorWalletAssetFlows();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<MonitorWalletAssetFlows> result = monitorWalletAssetFlowsService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

}
