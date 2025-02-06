/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset;

import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.asset.req.ReqWalletAssetFlowsPagination;
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
 * 钱包资产流水表 控制器
 * <p>File：WalletAssetFlowsController.java </p>
 * <p>Title: WalletAssetFlowsController </p>
 * <p>Description:WalletAssetFlowsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/asset/walletAssetFlows")
@Api(tags = "钱包资产流水")
public class WalletAssetFlowsController extends GenericController
{
    @Autowired(required = false)
    private WalletAssetFlowsService walletAssetFlowsService;

    @Autowired(required = false)
    private WalletAssetTransactionsService walletAssetTransactionsService;

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取钱包资产流水", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<WalletAssetFlows> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        WalletAssetFlows walletAssetFlowsDB = walletAssetFlowsService.selectByPrimaryKey(id);
        if(null == walletAssetFlowsDB || principal.getId().longValue() != walletAssetFlowsDB.getAccountId().longValue())
        {
            log.error("非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
//        //
//        RespWalletAssetFlows respWalletAssetFlows = new RespWalletAssetFlows();
//        //
//        BeanUtils.copyProperties(walletAssetFlowsDB, respWalletAssetFlows);
//        if(null != walletAssetFlowsDB.getOrgBusinessId() &&
//                (walletAssetFlowsDB.getBusinessType().equals(GlobalConst.BUSINESS_TYPE_DEPOSIT) || walletAssetFlowsDB.getBusinessType().equals(GlobalConst.BUSINESS_TYPE_WITHDRAW)) ){
//            WalletAssetTransactions walletAssetTransactionsDB = walletAssetTransactionsService.selectByPrimaryKey(walletAssetFlowsDB.getOrgBusinessId());
//            if(null != walletAssetTransactionsDB){
//                respWalletAssetFlows.setTrxNo(walletAssetTransactionsDB.getTrxNo());
//                respWalletAssetFlows.setTrxChannel(walletAssetTransactionsDB.getTrxChannel());
//            }
//        }
//        log.info("respWalletAssetFlows:{}", respWalletAssetFlows);
//        //
        return this.getJsonMessage(CommonEnums.SUCCESS, walletAssetFlowsDB);
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询钱包资产流水分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WalletAssetFlows>> data(@Validated @RequestBody ReqWalletAssetFlowsPagination reqWalletAssetFlowsPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        WalletAssetFlows walletAssetFlows = new WalletAssetFlows();
        BeanUtils.copyProperties(reqWalletAssetFlowsPagination, walletAssetFlows);
        walletAssetFlows.setAccountId(principal.getId());
        //
        PaginateResult<WalletAssetFlows> result = walletAssetFlowsService.search(reqWalletAssetFlowsPagination, walletAssetFlows);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
