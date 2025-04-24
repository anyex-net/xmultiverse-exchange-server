/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqRwaBalancesTransHistory;
import com.anyex.apps.controller.fund.req.ReqRwaBalancesTransHistoryPagination;
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

/**
 * RWA账户交易历史 控制器
 * <p>File：RwaBalancesTransHistoryController.java </p>
 * <p>Title: RwaBalancesTransHistoryController </p>
 * <p>Description:RwaBalancesTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/rwaBalancesTransHistory")
@Api(tags = "RWA账户交易历史")
public class RwaBalancesTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @Autowired(required = false)
    private BalancesService balancesService;

    @Autowired(required = false)
    private BalancesTransHistoryService balancesTransHistoryService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA账户交易历史列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaBalancesTransHistory>> data(@Validated @RequestBody ReqRwaBalancesTransHistoryPagination pagin) throws BusinessException
    {
        RwaBalancesTransHistory rwaBalancesTransHistoryQuery = new RwaBalancesTransHistory();
        BeanUtils.copyProperties(pagin, rwaBalancesTransHistoryQuery);
        rwaBalancesTransHistoryQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<RwaBalancesTransHistory> result = rwaBalancesTransHistoryService.search(pagin, rwaBalancesTransHistoryQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取RWA账户交易历史", httpMethod = "GET")
    public JsonMessage<RwaBalancesTransHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaBalancesTransHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/transferOut")
    @ApiOperation(value = "转出RWA账户转入资金账户/Spot", httpMethod = "POST")
    public JsonMessage transferOut(@Validated @RequestBody ReqRwaBalancesTransHistory reqRwaBalancesTransHistory) throws BusinessException
    {
        log.info("transferOut reqRwaBalancesTransHistory:{}", reqRwaBalancesTransHistory);
        Long userId = OnLineUserUtils.getPrincipal().getId();
        Long businessId = SerialnoUtils.buildPrimaryKey();
        // 转出方transferOut
        RwaBalances rwaBalancesSearch = new RwaBalances();
        rwaBalancesSearch.setUserId(userId);
        rwaBalancesSearch.setCurrency(reqRwaBalancesTransHistory.getCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesService.selectOne(rwaBalancesSearch);
        if(null != rwaBalancesDB && rwaBalancesDB.getBalance().compareTo(reqRwaBalancesTransHistory.getChangeAmt()) >= 0)
        {
            //
            RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
            BeanUtils.copyProperties(reqRwaBalancesTransHistory, rwaBalancesTransHistory);
            rwaBalancesTransHistory.setId(businessId);
            rwaBalancesTransHistory.setUserId(userId);
            rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getBalance());
            rwaBalancesTransHistory.setChangeAmt(reqRwaBalancesTransHistory.getChangeAmt());
            rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getBalance().subtract(reqRwaBalancesTransHistory.getChangeAmt()));
            rwaBalancesTransHistory.setBusinessId(String.valueOf(businessId));
            rwaBalancesTransHistory.setState("success");
            rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
            log.info("transferOut rwaBalancesTransHistory:{}", rwaBalancesTransHistory);
            rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
            //
            rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().subtract(reqRwaBalancesTransHistory.getChangeAmt()));
            rwaBalancesDB.setUpdateTime(System.currentTimeMillis());
            log.info("transferOut rwaBalancesDB:{}", rwaBalancesDB);
            rwaBalancesService.updateByPrimaryKeySelective(rwaBalancesDB);
        }
        //
        // 转入方transferIn
        if(reqRwaBalancesTransHistory.getToAcct().equals("spotAcct"))
        {
            //
            ReqAssetBalanceUpdate reqAssetBalanceUpdate = new ReqAssetBalanceUpdate();
            reqAssetBalanceUpdate.setUserId(userId);
            reqAssetBalanceUpdate.setCurrency(reqRwaBalancesTransHistory.getCurrency());
            reqAssetBalanceUpdate.setBusiness("transferIn");
            reqAssetBalanceUpdate.setBusinessId(businessId);
            reqAssetBalanceUpdate.setChange(String.valueOf(reqRwaBalancesTransHistory.getChangeAmt()));
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
        } else if(reqRwaBalancesTransHistory.getToAcct().equals("fundAcct"))
        {
            //
            Balances balancesSearch = new Balances();
            balancesSearch.setUserId(userId);
            balancesSearch.setCurrency(reqRwaBalancesTransHistory.getCurrency());
            Balances balancesDB = balancesService.selectOne(balancesSearch);
            if(null != balancesDB){
                //
                BalancesTransHistory balancesTransHistory = new BalancesTransHistory();
                BeanUtils.copyProperties(reqRwaBalancesTransHistory, balancesTransHistory);
                balancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                balancesTransHistory.setUserId(userId);
                balancesTransHistory.setType("transferIn");
                balancesTransHistory.setBeforeBal(balancesDB.getBalance());
                balancesTransHistory.setChangeAmt(reqRwaBalancesTransHistory.getChangeAmt());
                balancesTransHistory.setAfterBal(balancesDB.getBalance().add(reqRwaBalancesTransHistory.getChangeAmt()));
                balancesTransHistory.setBusinessId(String.valueOf(businessId));
                balancesTransHistory.setState("success");
                balancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("transferIn balancesTransHistory:{}", balancesTransHistory);
                balancesTransHistoryService.insert(balancesTransHistory);
                //
                balancesDB.setBalance(rwaBalancesDB.getBalance().add(reqRwaBalancesTransHistory.getChangeAmt()));
                balancesDB.setRemark("transferIn");
                balancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("transferIn balancesDB:{}", balancesDB);
                balancesService.updateByPrimaryKeySelective(balancesDB);
            } else {
                log.error("transferIn balancesDB is null");
                throw new BusinessException("transferIn balancesDB is null");
            }
        }
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
