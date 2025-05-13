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

//    @ResponseBody
//    @PostMapping(value = "/assetList")
//    @ApiOperation(value = "现货所有资产列表assetList", httpMethod = "POST")
//    public JsonMessage<JSONObject> assetList() throws BusinessException
//    {
//        JSONObject respJsonObject = ViabtcAssetApi.assetList();
//        log.info("assetList respJsonObject:{}", respJsonObject);
//        return getJsonMessage(CommonEnums.SUCCESS, respJsonObject);
//    }

    @ResponseBody
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
        // 处理错误情况
        if (respJsonObject.containsKey("error") && null != respJsonObject.get("error")) {
            JSONObject error = respJsonObject.getJSONObject("error");
            int code = error.getIntValue("code");
            String message = error.getString("message");
            log.error("[Error] Code: " + code + ", Message: " + message);
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        // 处理正常结果
        String spotAssetFreeze = null;
        String spotAssetAvailable = null;
        JSONObject result = respJsonObject.getJSONObject("result");
        for (String currency : result.keySet()) {
            JSONObject currencyData = result.getJSONObject(currency);
            spotAssetFreeze = currencyData.getString("freeze");
            spotAssetAvailable = currencyData.getString("available");
            log.info("currency:{}, spotAssetFreeze:{}, spotAssetAvailable:{}", currency, spotAssetFreeze, spotAssetAvailable);
        }
        log.info("spotAssetFreeze:{}, spotAssetAvailable:{}", spotAssetFreeze, spotAssetAvailable);
        //
        if(null != spotAssetAvailable && BigDecimal.valueOf(Double.valueOf(spotAssetAvailable)).compareTo(reqSpotAssetBalancesTransHistory.getChangeAmt()) >= 0)
        {
            //
            ReqAssetBalanceUpdate reqAssetBalanceUpdate = new ReqAssetBalanceUpdate();
            reqAssetBalanceUpdate.setUserId(userId);
            reqAssetBalanceUpdate.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
            reqAssetBalanceUpdate.setBusiness("transferOut");
            reqAssetBalanceUpdate.setBusinessId(businessId);
            reqAssetBalanceUpdate.setChange("-"+String.valueOf(reqSpotAssetBalancesTransHistory.getChangeAmt())); // 现货减少 要用负数
            // 更新明细说明Json对象
            JSONObject detailJsonObject = new JSONObject();
            detailJsonObject.put("detail", JSON.toJSONString(reqAssetBalanceUpdate));
            reqAssetBalanceUpdate.setDetail(detailJsonObject);
            log.info("transferOut reqAssetBalanceUpdate:{}", reqAssetBalanceUpdate);
            respJsonObject = ViabtcAssetApi.balanceUpdate(reqAssetBalanceUpdate);
            // 处理错误情况
            if (respJsonObject.containsKey("error") && null != respJsonObject.get("error")) {
                JSONObject error = respJsonObject.getJSONObject("error");
                int code = error.getIntValue("code");
                String message = error.getString("message");
                log.error("[Error] Code: " + code + ", Message: " + message);
                return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
            }
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
                //
                BalancesTransHistory balancesTransHistory = new BalancesTransHistory();
                BeanUtils.copyProperties(reqSpotAssetBalancesTransHistory, balancesTransHistory);
                balancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                balancesTransHistory.setUserId(userId);
                balancesTransHistory.setType("transferIn");
                balancesTransHistory.setBeforeBal(balancesDB.getBalance());
                balancesTransHistory.setChangeAmt(reqSpotAssetBalancesTransHistory.getChangeAmt());
                balancesTransHistory.setAfterBal(balancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
                balancesTransHistory.setBusinessId(String.valueOf(businessId));
                balancesTransHistory.setState("success");
                balancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("transferIn balancesTransHistory:{}", balancesTransHistory);
                balancesTransHistoryService.insert(balancesTransHistory);
                //
                balancesDB.setBalance(balancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
                balancesDB.setAvailBal(balancesDB.getBalance().subtract(balancesDB.getFrozenBal()));
                balancesDB.setRemark("transferIn");
                balancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn balancesDB:{}", balancesDB);
                balancesService.updateByPrimaryKeySelective(balancesDB);
            } else {
                log.error("transferIn balancesDB is null");
                throw new BusinessException("transferIn balancesDB is null");
            }
            //
        } else if(reqSpotAssetBalancesTransHistory.getToAcct().equals("rwaAcct"))
        {
            //
            RwaBalances rwaBalancesSearch = new RwaBalances();
            rwaBalancesSearch.setUserId(userId);
            rwaBalancesSearch.setCurrency(reqSpotAssetBalancesTransHistory.getCurrency());
            RwaBalances rwaBalancesDB = rwaBalancesService.selectOne(rwaBalancesSearch);
            if(null != rwaBalancesDB){
                //
                RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
                BeanUtils.copyProperties(reqSpotAssetBalancesTransHistory, rwaBalancesTransHistory);
                rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                rwaBalancesTransHistory.setUserId(userId);
                rwaBalancesTransHistory.setType("transferIn");
                rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getBalance());
                rwaBalancesTransHistory.setChangeAmt(reqSpotAssetBalancesTransHistory.getChangeAmt());
                rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
                rwaBalancesTransHistory.setBusinessId(String.valueOf(businessId));
                rwaBalancesTransHistory.setState("success");
                rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesTransHistory:{}", rwaBalancesTransHistory);
                rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
                //
                rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().add(reqSpotAssetBalancesTransHistory.getChangeAmt()));
                rwaBalancesDB.setAvailBal(rwaBalancesDB.getBalance().subtract(rwaBalancesDB.getFrozenBal()));
                rwaBalancesDB.setRemark("transferIn");
                rwaBalancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesDB:{}", rwaBalancesDB);
                rwaBalancesService.updateByPrimaryKeySelective(rwaBalancesDB);
            } else {
                //
                RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
                BeanUtils.copyProperties(reqSpotAssetBalancesTransHistory, rwaBalancesTransHistory);
                rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                rwaBalancesTransHistory.setUserId(userId);
                rwaBalancesTransHistory.setType("transferIn");
                rwaBalancesTransHistory.setBeforeBal(BigDecimal.ZERO);
                rwaBalancesTransHistory.setChangeAmt(reqSpotAssetBalancesTransHistory.getChangeAmt());
                rwaBalancesTransHistory.setAfterBal(reqSpotAssetBalancesTransHistory.getChangeAmt());
                rwaBalancesTransHistory.setBusinessId(String.valueOf(businessId));
                rwaBalancesTransHistory.setState("success");
                rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesTransHistory:{}", rwaBalancesTransHistory);
                rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
                //
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
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
