/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqBalancesTransHistory;
import com.anyex.apps.controller.fund.req.ReqBalancesTransHistoryPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.Balances;
import com.anyex.apps.fund.entity.BalancesTransHistory;
import com.anyex.apps.fund.service.BalancesService;
import com.anyex.apps.fund.service.BalancesTransHistoryService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.entity.RwaBalancesTransHistory;
import com.anyex.apps.rwa.service.RwaBalancesService;
import com.anyex.apps.rwa.service.RwaBalancesTransHistoryService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.exchange.viabtc.api.ViabtcAssetApi;
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
 * 资金账户交易历史 控制器
 * <p>File：BalancesTransHistoryController.java </p>
 * <p>Title: BalancesTransHistoryController </p>
 * <p>Description:BalancesTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/balancesTransHistory")
@Api(tags = "资金账户交易历史")
public class BalancesTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private BalancesService balancesService;

    @Autowired(required = false)
    private BalancesTransHistoryService balancesTransHistoryService;

    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询资金账户交易历史列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<BalancesTransHistory>> data(@Validated @RequestBody ReqBalancesTransHistoryPagination pagin) throws BusinessException
    {
        BalancesTransHistory balancesTransHistoryQuery = new BalancesTransHistory();
        BeanUtils.copyProperties(pagin, balancesTransHistoryQuery);
        balancesTransHistoryQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<BalancesTransHistory> result = balancesTransHistoryService.search(pagin, balancesTransHistoryQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取资金账户交易历史", httpMethod = "GET")
    public JsonMessage<BalancesTransHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, balancesTransHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/transferOut")
    @ApiOperation(value = "转出资金账户转入Spot/Rwa", httpMethod = "POST")
    public JsonMessage transferOut(@Validated @RequestBody ReqBalancesTransHistory reqBalancesTransHistory) throws BusinessException
    {
        log.info("transferOut reqBalancesTransHistory:{}", reqBalancesTransHistory);
        Long userId = OnLineUserUtils.getPrincipal().getId();
        Long businessId = SerialnoUtils.buildPrimaryKey();
        // 转出方transferOut
        Balances balancesSearch = new Balances();
        balancesSearch.setUserId(userId);
        balancesSearch.setCurrency(reqBalancesTransHistory.getCurrency());
        Balances balancesDB = balancesService.selectOne(balancesSearch);
        if(null != balancesDB && balancesDB.getBalance().compareTo(reqBalancesTransHistory.getChangeAmt()) >= 0)
        {
            //
            BalancesTransHistory balancesTransHistory = new BalancesTransHistory();
            BeanUtils.copyProperties(reqBalancesTransHistory, balancesTransHistory);
            balancesTransHistory.setId(businessId);
            balancesTransHistory.setUserId(userId);
            balancesTransHistory.setBeforeBal(balancesDB.getBalance());
            balancesTransHistory.setChangeAmt(reqBalancesTransHistory.getChangeAmt());
            balancesTransHistory.setAfterBal(balancesDB.getBalance().subtract(reqBalancesTransHistory.getChangeAmt()));
            balancesTransHistory.setBusinessId(String.valueOf(businessId));
            balancesTransHistory.setState("success");
            balancesTransHistory.setCreateTime(System.currentTimeMillis());
            log.info("transferOut balancesTransHistory:{}", balancesTransHistory);
            balancesTransHistoryService.insert(balancesTransHistory);
            //
            balancesDB.setBalance(balancesDB.getBalance().subtract(reqBalancesTransHistory.getChangeAmt()));
            balancesDB.setUpdateTime(System.currentTimeMillis());
            log.info("transferOut balancesDB:{}", balancesDB);
            balancesService.updateByPrimaryKeySelective(balancesDB);
        }
        //
        // 转入方transferIn
        if(reqBalancesTransHistory.getToAcct().equals("spotAcct"))
        {
            //
            ReqAssetBalanceUpdate reqAssetBalanceUpdate = new ReqAssetBalanceUpdate();
            reqAssetBalanceUpdate.setUserId(userId);
            reqAssetBalanceUpdate.setCurrency(reqBalancesTransHistory.getCurrency());
            reqAssetBalanceUpdate.setBusiness("transferIn");
            reqAssetBalanceUpdate.setBusinessId(businessId);
            reqAssetBalanceUpdate.setChange(String.valueOf(reqBalancesTransHistory.getChangeAmt()));
            // 更新明细说明Json对象
            JSONObject detailJsonObject = new JSONObject();
            detailJsonObject.put("detail", JSON.toJSONString(reqAssetBalanceUpdate));
            reqAssetBalanceUpdate.setDetail(detailJsonObject);
            log.info("transferIn reqAssetBalanceUpdate:{}", reqAssetBalanceUpdate);
            JSONObject respJsonObject = ViabtcAssetApi.balanceUpdate(reqAssetBalanceUpdate);
            // 处理错误情况
            if (respJsonObject.containsKey("error") && null != respJsonObject.get("error")) {
                JSONObject error = respJsonObject.getJSONObject("error");
                int code = error.getIntValue("code");
                String message = error.getString("message");
                log.error("[Error] Code: " + code + ", Message: " + message);
                return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
            }
        } else if(reqBalancesTransHistory.getToAcct().equals("rwaAcct"))
        {
            //
            RwaBalances rwaBalancesSearch = new RwaBalances();
            rwaBalancesSearch.setUserId(userId);
            rwaBalancesSearch.setCurrency(reqBalancesTransHistory.getCurrency());
            RwaBalances rwaBalancesDB = rwaBalancesService.selectOne(rwaBalancesSearch);
            if(null != rwaBalancesDB){
                //
                RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
                BeanUtils.copyProperties(reqBalancesTransHistory, rwaBalancesTransHistory);
                rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                rwaBalancesTransHistory.setUserId(userId);
                rwaBalancesTransHistory.setType("transferIn");
                rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getBalance());
                rwaBalancesTransHistory.setChangeAmt(reqBalancesTransHistory.getChangeAmt());
                rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getBalance().add(reqBalancesTransHistory.getChangeAmt()));
                rwaBalancesTransHistory.setBusinessId(String.valueOf(businessId));
                rwaBalancesTransHistory.setState("success");
                rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesTransHistory:{}", rwaBalancesTransHistory);
                rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
                //
                rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().add(reqBalancesTransHistory.getChangeAmt()));
                rwaBalancesDB.setRemark("transferIn");
                rwaBalancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesDB:{}", rwaBalancesDB);
                rwaBalancesService.updateByPrimaryKeySelective(rwaBalancesDB);
            } else {
                //
                rwaBalancesDB = new RwaBalances();
                rwaBalancesDB.setUserId(userId);
                rwaBalancesDB.setCurrency(reqBalancesTransHistory.getCurrency());
                rwaBalancesDB.setBalance(reqBalancesTransHistory.getChangeAmt());
                rwaBalancesDB.setFrozenBal(BigDecimal.ZERO);
                rwaBalancesDB.setAvailBal(rwaBalancesDB.getBalance());
                rwaBalancesDB.setRemark("transferIn");
                rwaBalancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesDB:{}", rwaBalancesDB);
                rwaBalancesService.insert(rwaBalancesDB);
                //
                RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
                BeanUtils.copyProperties(reqBalancesTransHistory, rwaBalancesTransHistory);
                rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                rwaBalancesTransHistory.setUserId(userId);
                rwaBalancesTransHistory.setType("transferIn");
                rwaBalancesTransHistory.setBeforeBal(BigDecimal.ZERO);
                rwaBalancesTransHistory.setChangeAmt(reqBalancesTransHistory.getChangeAmt());
                rwaBalancesTransHistory.setAfterBal(reqBalancesTransHistory.getChangeAmt());
                rwaBalancesTransHistory.setBusinessId(String.valueOf(businessId));
                rwaBalancesTransHistory.setState("success");
                rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("transferIn rwaBalancesTransHistory:{}", rwaBalancesTransHistory);
                rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
            }
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
