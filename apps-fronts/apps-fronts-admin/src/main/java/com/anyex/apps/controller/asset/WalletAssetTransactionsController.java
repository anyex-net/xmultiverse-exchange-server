/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.asset.req.ReqWalletAssetTransactionsPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.anyex.apps.asset.entity.WalletAssetTransactions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 钱包资产转账记录表 控制器
 * <p>File：WalletAssetTransactionsController.java </p>
 * <p>Title: WalletAssetTransactionsController </p>
 * <p>Description:WalletAssetTransactionsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/asset/walletAssetTransactions")
@Api(tags = "钱包资产转账记录")
public class WalletAssetTransactionsController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetTransactionsService walletAssetTransactionsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("asset:walletAssetTransactions:data")
    @ApiOperation(value = "根据ID取钱包资产转账记录", httpMethod = "GET")
    public JsonMessage<WalletAssetTransactions> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetTransactionsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("asset:walletAssetTransactions:data")
    @ApiOperation(value = "查询钱包资产转账记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAssetTransactions>> data(@Validated @ModelAttribute ReqWalletAssetTransactionsPagination reqWalletAssetFlowsPagination) throws BusinessException
    {
        WalletAssetTransactions walletAssetTransactions = new WalletAssetTransactions();
        BeanUtils.copyProperties(reqWalletAssetFlowsPagination, walletAssetTransactions);
        //
        PaginateResult<WalletAssetTransactions> result = walletAssetTransactionsService.search(reqWalletAssetFlowsPagination, walletAssetTransactions);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
