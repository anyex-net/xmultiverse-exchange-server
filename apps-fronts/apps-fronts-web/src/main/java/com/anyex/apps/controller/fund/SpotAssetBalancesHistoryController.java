/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqSpotAssetBalanceHistory;
import com.anyex.apps.controller.fund.req.ReqSpotAssetBalancesTransHistory;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.Balances;
import com.anyex.apps.fund.entity.BalancesTransHistory;
import com.anyex.apps.fund.service.BalancesService;
import com.anyex.apps.fund.service.BalancesTransHistoryService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.entity.RwaBalancesTransHistory;
import com.anyex.apps.rwa.service.RwaBalancesService;
import com.anyex.apps.rwa.service.RwaBalancesTransHistoryService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceHistory;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceQuery;
import com.anyex.exchange.viabtc.req.ReqAssetBalanceUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 现货资产账户变动历史SpotAssetBalancesHistory 控制器
 * <p>File：SpotAssetBalancesHistoryController.java </p>
 * <p>Title: SpotAssetBalancesHistoryController </p>
 * <p>Description:SpotAssetBalancesHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/spotAssetBalances")
@Api(tags = "现货账户资产变动历史")
public class SpotAssetBalancesHistoryController extends GenericController
{

    @Autowired(required = false)
    private BalancesService balancesService;

    @Autowired(required = false)
    private BalancesTransHistoryService balancesTransHistoryService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @ResponseBody
    @PostMapping(value = "/assetBalanceHistory")
    @ApiOperation(value = "现货资产变动历史", httpMethod = "POST")
    public JsonMessage<JSONObject> assetBalanceHistory(@Validated @RequestBody ReqSpotAssetBalanceHistory reqSpotAssetBalanceHistory) throws BusinessException
    {
        log.info("assetBalanceHistory reqSpotAssetBalanceHistory:{}", reqSpotAssetBalanceHistory);
        ReqAssetBalanceHistory reqAssetBalanceHistory = new ReqAssetBalanceHistory();
        BeanUtils.copyProperties(reqSpotAssetBalanceHistory, reqAssetBalanceHistory);
        reqAssetBalanceHistory.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        JSONObject respJsonObject = ViabtcAssetApi.balanceHistory(reqAssetBalanceHistory);
        log.info("assetBalanceHistory respJsonObject:{}", respJsonObject);
        //
        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
    }

    @PostMapping(value = "/transferOut")
    @ApiOperation(value = "转出Spot账户转入资金账户/Rwa", httpMethod = "POST")
    public JsonMessage transferOut(@Validated @RequestBody ReqSpotAssetBalancesTransHistory reqSpotAssetBalancesTransHistory) throws BusinessException
    {
        log.info("transferOut reqSpotAssetBalancesTransHistory:{}", reqSpotAssetBalancesTransHistory);
        Long userId = OnLineUserUtils.getPrincipal().getId();
        Long businessId = SerialnoUtils.buildPrimaryKey();
        // 转出方transferOut
        ReqAssetBalanceQuery reqAssetBalanceQuery = new ReqAssetBalanceQuery();
        reqAssetBalanceQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        reqAssetBalanceQuery.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
        log.info("transferOut reqAssetBalanceQuery:{}", reqAssetBalanceQuery);
        JSONObject respJsonObject = ViabtcAssetApi.balanceQuery(reqAssetBalanceQuery);
        log.info("transferOut respJsonObject:{}", respJsonObject);
        //
        RwaBalances rwaBalancesSearchTemp = new RwaBalances();
        rwaBalancesSearchTemp.setUserId(userId);
        rwaBalancesSearchTemp.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
        RwaBalances rwaBalancesDBTemp = rwaBalancesService.selectOne(rwaBalancesSearchTemp);
        if(null != rwaBalancesDBTemp && rwaBalancesDBTemp.getBalance().compareTo(reqSpotAssetBalancesTransHistory.getChangeAmt()) >= 0)
        {
            //
            ReqAssetBalanceUpdate reqAssetBalanceUpdate = new ReqAssetBalanceUpdate();
            reqAssetBalanceUpdate.setUserId(userId);
            reqAssetBalanceUpdate.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
            reqAssetBalanceUpdate.setBusiness("transferOut");
            reqAssetBalanceUpdate.setBusinessId(businessId);
            reqAssetBalanceUpdate.setChange(String.valueOf(reqSpotAssetBalancesTransHistory.getChangeAmt()));
            // 更新明细说明Json对象
            JSONObject detailJsonObject = new JSONObject();
            detailJsonObject.put("detail", JSON.toJSONString(reqAssetBalanceUpdate));
            reqAssetBalanceUpdate.setDetail(detailJsonObject);
            log.info("transferOut reqAssetBalanceUpdate:{}", reqAssetBalanceUpdate);
            ViabtcAssetApi.balanceUpdate(reqAssetBalanceUpdate);
        }
        //
        // 转入方transferIn
        if(reqSpotAssetBalancesTransHistory.getToAcct().equals("fundAcct"))
        {
            //
            Balances balancesSearch = new Balances();
            balancesSearch.setUserId(userId);
            balancesSearch.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
            Balances balancesDB = balancesService.selectOne(balancesSearch);
            if(null != balancesDB){
                balancesDB.setBalance(rwaBalancesDBTemp.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
                balancesDB.setRemark("transferIn");
                balancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn balancesDB:{}", balancesDB);
                balancesService.updateByPrimaryKeySelective(balancesDB);
            }
            //
            BalancesTransHistory balancesTransHistory = new BalancesTransHistory();
            BeanUtils.copyProperties(reqSpotAssetBalancesTransHistory, balancesTransHistory);
            balancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
            balancesTransHistory.setUserId(userId);
            balancesTransHistory.setBeforeBal(balancesDB.getBalance());
            balancesTransHistory.setChangeAmt(reqSpotAssetBalancesTransHistory.getChangeAmt());
            balancesTransHistory.setAfterBal(balancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
            balancesTransHistory.setBusinessId(String.valueOf(businessId));
            balancesTransHistory.setCreateTime(System.currentTimeMillis());
            log.info("transferIn balancesTransHistory:{}", balancesTransHistory);
            balancesTransHistoryService.insert(balancesTransHistory);
            //
        } else if(reqSpotAssetBalancesTransHistory.getToAcct().equals("rwaAcct"))
        {
            //
            RwaBalances rwaBalancesSearch = new RwaBalances();
            rwaBalancesSearch.setUserId(userId);
            rwaBalancesSearch.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
            RwaBalances rwaBalancesDB = rwaBalancesService.selectOne(rwaBalancesSearch);
            if(null != rwaBalancesDB){
                rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
                rwaBalancesDB.setRemark("transferIn");
                rwaBalancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesDB:{}", rwaBalancesDB);
                rwaBalancesService.updateByPrimaryKeySelective(rwaBalancesDB);
            } else {
                rwaBalancesDB = new RwaBalances();
                rwaBalancesDB.setUserId(userId);
                rwaBalancesDB.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
                rwaBalancesDB.setBalance(reqSpotAssetBalancesTransHistory.getChangeAmt());
                rwaBalancesDB.setFrozenBal(BigDecimal.ZERO);
                rwaBalancesDB.setAvailBal(rwaBalancesDB.getBalance());
                rwaBalancesDB.setRemark("transferIn");
                rwaBalancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesDB:{}", rwaBalancesDB);
                rwaBalancesService.insert(rwaBalancesDB);
            }
            //
            RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
            BeanUtils.copyProperties(reqSpotAssetBalancesTransHistory, rwaBalancesTransHistory);
            rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
            rwaBalancesTransHistory.setUserId(userId);
            rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getBalance());
            rwaBalancesTransHistory.setChangeAmt(reqSpotAssetBalancesTransHistory.getChangeAmt());
            rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
            rwaBalancesTransHistory.setBusinessId(String.valueOf(businessId));
            rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
            log.info("transferIn rwaBalancesTransHistory:{}", rwaBalancesTransHistory);
            rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
            //
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
