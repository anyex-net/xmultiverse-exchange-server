/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqWithdrawalHistoryPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.WithdrawalHistory;
import com.anyex.apps.fund.service.WithdrawalHistoryService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private WithdrawalHistoryService withdrawalHistoryService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询提现历史列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<WithdrawalHistory>> data(@Validated @RequestBody ReqWithdrawalHistoryPagination pagin) throws BusinessException
    {
        WithdrawalHistory withdrawalHistoryQuery = new WithdrawalHistory();
        BeanUtils.copyProperties(pagin, withdrawalHistoryQuery);
        withdrawalHistoryQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<WithdrawalHistory> result = withdrawalHistoryService.search(pagin, withdrawalHistoryQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取提现历史", httpMethod = "GET")
    public JsonMessage<WithdrawalHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, withdrawalHistoryService.selectByPrimaryKey(id));
    }
}
