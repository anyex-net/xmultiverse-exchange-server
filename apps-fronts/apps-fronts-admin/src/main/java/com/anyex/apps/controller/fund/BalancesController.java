/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.controller.fund.req.ReqBalancesTransHistory;
import com.anyex.apps.fund.entity.BalancesTransHistory;
import com.anyex.apps.fund.service.BalancesTransHistoryService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.fund.entity.Balances;
import com.anyex.apps.fund.service.BalancesService;

import com.anyex.apps.controller.fund.req.ReqBalancesPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 资金账户余额 控制器
 * <p>File：BalancesController.java </p>
 * <p>Title: BalancesController </p>
 * <p>Description:BalancesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/balances")
@Api(tags = "资金账户余额")
public class BalancesController extends GenericController
{
    @Autowired(required = false)
    private BalancesService balancesService;

    @Autowired(required = false)
    private BalancesTransHistoryService balancesTransHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("fund:balances:data")
    @ApiOperation(value = "根据ID取资金账户余额", httpMethod = "GET")
    public JsonMessage<Balances> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, balancesService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("fund:balances:data")
    @ApiOperation(value = "查询资金账户余额", httpMethod = "POST")
    public JsonMessage<PaginateResult<Balances>> data(@ModelAttribute ReqBalancesPagination pagin) throws BusinessException
    {
        Balances entity = new Balances();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<Balances> result = balancesService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/adjust")
    @RequiresPermissions("fund:balances:operator")
    @ApiOperation(value = "调整资金账户余额", httpMethod = "POST")
    public JsonMessage adjust(@ModelAttribute ReqBalancesTransHistory reqBalancesTransHistory) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqBalancesTransHistory))
        {
            Balances balancesSearch = new Balances();
//            balancesSearch.setUserId(OnLineUserUtils.getPrincipal().getId());
            balancesSearch.setId(reqBalancesTransHistory.getId());
            balancesSearch.setCurrency(reqBalancesTransHistory.getCurrency());
            Balances balancesDB = balancesService.selectOne(balancesSearch);
            if(null != balancesDB){
                //
                BalancesTransHistory balancesTransHistory = new BalancesTransHistory();
                BeanUtils.copyProperties(balancesDB, balancesTransHistory);
                balancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
                balancesTransHistory.setUserId(balancesDB.getUserId());
                balancesTransHistory.setType(reqBalancesTransHistory.getType()); // adjustAdd、adjustSub
                balancesTransHistory.setBeforeBal(balancesDB.getBalance());
                balancesTransHistory.setChangeAmt(reqBalancesTransHistory.getChangeAmt());
                if ("adjustAdd".equals(reqBalancesTransHistory.getType())){
                    balancesTransHistory.setAfterBal(balancesDB.getBalance().add(reqBalancesTransHistory.getChangeAmt()));
                } else if ("adjustSub".equals(reqBalancesTransHistory.getType())){
                    balancesTransHistory.setAfterBal(balancesDB.getBalance().subtract(reqBalancesTransHistory.getChangeAmt()));
                }
                balancesTransHistory.setTransDesc(reqBalancesTransHistory.getTransDesc());
                balancesTransHistory.setState("success");
                balancesTransHistory.setCreateTime(System.currentTimeMillis());
                log.info("balancesTransHistory:{}", balancesTransHistory);
                balancesTransHistoryService.insert(balancesTransHistory);
                //
//                balancesDB.setBalance(balancesDB.getBalance().add(reqBalancesTransHistory.getChangeAmt()));
                balancesDB.setRemark(reqBalancesTransHistory.getType());
                if ("adjustAdd".equals(reqBalancesTransHistory.getType())){
                    balancesDB.setBalance(balancesDB.getBalance().add(reqBalancesTransHistory.getChangeAmt()));
                    balancesDB.setAvailBal(balancesDB.getAvailBal().add(reqBalancesTransHistory.getChangeAmt()));
                } else if ("adjustSub".equals(reqBalancesTransHistory.getType())){
                    balancesDB.setBalance(balancesDB.getBalance().subtract(reqBalancesTransHistory.getChangeAmt()));
                    balancesDB.setAvailBal(balancesDB.getAvailBal().subtract(reqBalancesTransHistory.getChangeAmt()));
                }
                balancesDB.setUpdateTime(System.currentTimeMillis());
                log.info("balancesDB:{}", balancesDB);
                balancesService.updateByPrimaryKeySelective(balancesDB);
            } else {
                log.error("balancesDB is null 请检查相关资金账户数据!");
                throw new BusinessException("balancesDB is null, please check fund acct data!");
            }
        }
        //
        return json;
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("fund:balances:operator")
//    @ApiOperation(value = "保存资金账户余额", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqBalances info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            Balances entity = new Balances();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//                entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                balancesService.insert(entity);
//            } else {
//                balancesService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("fund:balances:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        balancesService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
