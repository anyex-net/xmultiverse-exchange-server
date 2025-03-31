/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqBalancesPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.Balances;
import com.anyex.apps.fund.service.BalancesService;
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

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询资金账户余额列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Balances>> data(@Validated @RequestBody ReqBalancesPagination pagin) throws BusinessException
    {
        Balances balancesQuery = new Balances();
        BeanUtils.copyProperties(pagin, balancesQuery);
        balancesQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<Balances> result = balancesService.search(pagin, balancesQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取资金账户余额", httpMethod = "GET")
    public JsonMessage<Balances> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, balancesService.selectByPrimaryKey(id));
    }
}
