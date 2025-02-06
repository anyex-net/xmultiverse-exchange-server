/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.asset.req.ReqWalletAssetTransactionsPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
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

/**
 * 钱包资产转账记录 控制器
 * <p>File：WalletAssetTransactionsController.java </p>
 * <p>Title: WalletAssetTransactionsController </p>
 * <p>Description:WalletAssetTransactionsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/asset/walletAssetTransactions")
@Api(tags = "钱包资产转账记录")
public class WalletAssetTransactionsController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetTransactionsService walletAssetTransactionsService;

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取钱包资产转账记录", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<WalletAssetTransactions> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        WalletAssetTransactions walletAssetTransactionsDB = walletAssetTransactionsService.selectByPrimaryKey(id);
        if(null == walletAssetTransactionsDB || principal.getId().longValue() != walletAssetTransactionsDB.getAccountId().longValue())
        {
            log.error("非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetTransactionsDB);
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询钱包资产转账记录分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAssetTransactions>> data(@Validated @RequestBody ReqWalletAssetTransactionsPagination reqWalletAssetFlowsPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        WalletAssetTransactions walletAssetTransactions = new WalletAssetTransactions();
        BeanUtils.copyProperties(reqWalletAssetFlowsPagination, walletAssetTransactions);
        walletAssetTransactions.setAccountId(principal.getId());
        //
        PaginateResult<WalletAssetTransactions> result = walletAssetTransactionsService.search(reqWalletAssetFlowsPagination, walletAssetTransactions);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
