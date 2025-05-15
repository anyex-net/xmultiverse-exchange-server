/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.controller.fund.req.ReqWithdrawalHistory;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.WithdrawalHistory;
import com.anyex.apps.fund.service.WithdrawalHistoryService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 提现 控制器
 * <p>File：WithdrawalController.java </p>
 * <p>Title: WithdrawalController </p>
 * <p>Description:WithdrawalController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/withdrawal")
@Api(tags = "提现")
public class WithdrawalController extends GenericController
{
    @Autowired(required = false)
    private WithdrawalHistoryService withdrawalHistoryService;

    @PostMapping(value = "/withdrawalApply")
    @ApiOperation(value = "提现申请", httpMethod = "POST")
    public JsonMessage withdrawalApply(@Validated @RequestBody ReqWithdrawalHistory reqWithdrawalHistory) throws BusinessException
    {
        //
        // 判断可用 需要补充
        //
        WithdrawalHistory withdrawalHistory = new WithdrawalHistory();
        BeanUtils.copyProperties(reqWithdrawalHistory, withdrawalHistory);
        withdrawalHistory.setUserId(OnLineUserUtils.getPrincipal().getId());
        withdrawalHistory.setFromAddress("fromWalletAddress");
        withdrawalHistory.setFee(BigDecimal.ZERO);
        withdrawalHistory.setTransId(SerialnoUtils.getOrderNum());
        withdrawalHistory.setState("applied");
        withdrawalHistory.setCreateTime(System.currentTimeMillis());
        log.info("withdrawalHistory:{}", withdrawalHistory);
        withdrawalHistoryService.insert(withdrawalHistory);
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/withdrawalApplyCancel")
    @ApiOperation(value = "提现申请撤销", httpMethod = "POST")
    public JsonMessage withdrawalApplyCancel(@Validated @RequestBody ReqIdParam reqIdParam) throws BusinessException
    {
        //
        WithdrawalHistory withdrawalHistoryDB = withdrawalHistoryService.selectByPrimaryKey(reqIdParam.getId());
        // 存在并且是申请中
        if(null != withdrawalHistoryDB && withdrawalHistoryDB.getState().equals("applied")) {
            withdrawalHistoryDB.setState("canceled");
            withdrawalHistoryDB.setUpdateTime(System.currentTimeMillis());
            log.info("withdrawalHistoryDB:{}", withdrawalHistoryDB);
            withdrawalHistoryService.updateByPrimaryKeySelective(withdrawalHistoryDB);
        }
        //
        // 处理资产解冻
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
