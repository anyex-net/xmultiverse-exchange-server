/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.asset.req.ReqWalletAssetFlowsPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.asset.entity.WalletAssetFlows;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 钱包资产流水表 控制器
 * <p>File：WalletAssetFlowsController.java </p>
 * <p>Title: WalletAssetFlowsController </p>
 * <p>Description:WalletAssetFlowsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/asset/walletAssetFlows")
@Api(tags = "钱包资产流水")
public class WalletAssetFlowsController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetFlowsService walletAssetFlowsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("asset:walletAssetFlows:data")
    @ApiOperation(value = "根据ID取钱包资产流水", httpMethod = "GET")
    public JsonMessage<WalletAssetFlows> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetFlowsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("asset:walletAssetFlows:data")
    @ApiOperation(value = "查询钱包资产流水分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAssetFlows>> data(@Validated @ModelAttribute ReqWalletAssetFlowsPagination reqWalletAssetFlowsPagination) throws BusinessException
    {
        //
        WalletAssetFlows walletAssetFlows = new WalletAssetFlows();
        BeanUtils.copyProperties(reqWalletAssetFlowsPagination, walletAssetFlows);
        //
        PaginateResult<WalletAssetFlows> result = walletAssetFlowsService.search(reqWalletAssetFlowsPagination, walletAssetFlows);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
