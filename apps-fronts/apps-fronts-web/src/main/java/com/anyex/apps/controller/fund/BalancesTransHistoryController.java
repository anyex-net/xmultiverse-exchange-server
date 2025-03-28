/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqBalancesTransHistoryPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.BalancesTransHistory;
import com.anyex.apps.fund.service.BalancesTransHistoryService;
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
    private BalancesTransHistoryService balancesTransHistoryService;

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
}
