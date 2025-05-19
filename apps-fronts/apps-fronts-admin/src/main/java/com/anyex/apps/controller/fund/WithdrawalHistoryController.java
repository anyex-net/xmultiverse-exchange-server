/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.wallet.XMWalletApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.fund.entity.WithdrawalHistory;
import com.anyex.apps.fund.service.WithdrawalHistoryService;

import com.anyex.apps.controller.fund.req.ReqWithdrawalHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 提现历史 控制器
 * <p>File：WithdrawalHistoryController.java </p>
 * <p>Title: WithdrawalHistoryController </p>
 * <p>Description:WithdrawalHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/withdrawalHistory")
@Api(tags = "提现历史")
public class WithdrawalHistoryController extends GenericController
{
    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private WithdrawalHistoryService withdrawalHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("fund:withdrawalHistory:data")
    @ApiOperation(value = "根据ID取提现历史", httpMethod = "GET")
    public JsonMessage<WithdrawalHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, withdrawalHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("fund:withdrawalHistory:data")
    @ApiOperation(value = "查询提现历史", httpMethod = "POST")
    public JsonMessage<PaginateResult<WithdrawalHistory>> data(@ModelAttribute ReqWithdrawalHistoryPagination pagin) throws BusinessException
    {
        WithdrawalHistory entity = new WithdrawalHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<WithdrawalHistory> result = withdrawalHistoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/check")
    @RequiresPermissions("fund:withdrawalHistory:check")
    @ApiOperation(value = "提现复核", httpMethod = "POST")
    public JsonMessage check(Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        WithdrawalHistory withdrawalHistoryDB = withdrawalHistoryService.selectByPrimaryKey(id);
        if (principal != null) {
            withdrawalHistoryDB.setCheckBy(principal.getUserName());
        }
        withdrawalHistoryDB.setCheckTime(System.currentTimeMillis());
        withdrawalHistoryDB.setState("checked");
        log.info("updateByPrimaryKeySelective withdrawalHistoryDB:{}", withdrawalHistoryDB);
        withdrawalHistoryService.updateByPrimaryKeySelective(withdrawalHistoryDB);
        //

        //
        // 调用钱包接口发起提现交易
        User userDB = userService.selectByPrimaryKey(withdrawalHistoryDB.getUserId()); // 注意这里是提现记录中的发起用户ID
        //
        // 5.发送交易
        String userNo = userDB.getRemark();
        JSONObject jsonObjectResp = XMWalletApi.create_transaction(userNo, withdrawalHistoryDB.getCurrency(), withdrawalHistoryDB.getBlockchain(),
                String.valueOf(withdrawalHistoryDB.getAmount()), withdrawalHistoryDB.getToAddress());
        log.info("create_transaction jsonObjectResp:{}", jsonObjectResp);
        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
            JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
            log.info("create_transaction jsonObjectData request_no: {}", jsonObjectData.getString("request_no"));
            // request_no: 771d4761-8dc0-4e6e-a35a-30f8d07f85c0
            //
            withdrawalHistoryDB.setTransId(jsonObjectData.getString("request_no")); // 唯一的
            withdrawalHistoryDB.setState("exporting");
            withdrawalHistoryDB.setUpdateTime(System.currentTimeMillis());
            log.info("withdrawalHistoryDB:{}", withdrawalHistoryDB);
            withdrawalHistoryService.updateByPrimaryKeySelective(withdrawalHistoryDB);
            return getJsonMessage(CommonEnums.SUCCESS);
            //
        } else {
            log.error("create_transaction error: {}", jsonObjectResp);
            throw new BusinessException("create_transaction error");
        }
        //
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("fund:withdrawalHistory:operator")
//    @ApiOperation(value = "保存提现历史", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqWithdrawalHistory info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            WithdrawalHistory entity = new WithdrawalHistory();
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
//                withdrawalHistoryService.insert(entity);
//            } else {
//                withdrawalHistoryService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("fund:withdrawalHistory:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        withdrawalHistoryService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
