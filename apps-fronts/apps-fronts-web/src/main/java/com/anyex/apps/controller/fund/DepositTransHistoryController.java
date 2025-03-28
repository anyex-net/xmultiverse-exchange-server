/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqDepositTransHistoryPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.DepositTransHistory;
import com.anyex.apps.fund.service.DepositTransHistoryService;
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
 * 充值交易历史 控制器
 * <p>File：DepositTransHistoryController.java </p>
 * <p>Title: DepositTransHistoryController </p>
 * <p>Description:DepositTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/depositTransHistory")
@Api(tags = "充值交易历史")
public class DepositTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private DepositTransHistoryService depositTransHistoryService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询充值交易历史列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<DepositTransHistory>> data(@Validated @RequestBody ReqDepositTransHistoryPagination pagin) throws BusinessException
    {
        DepositTransHistory depositTransHistoryQuery = new DepositTransHistory();
        BeanUtils.copyProperties(pagin, depositTransHistoryQuery);
        depositTransHistoryQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<DepositTransHistory> result = depositTransHistoryService.search(pagin, depositTransHistoryQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取充值交易历史", httpMethod = "GET")
    public JsonMessage<DepositTransHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, depositTransHistoryService.selectByPrimaryKey(id));
    }
}
