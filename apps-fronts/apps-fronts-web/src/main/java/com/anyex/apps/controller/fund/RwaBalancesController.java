/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqRwaBalancesPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.service.RwaBalancesService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RWA账户余额 控制器
 * <p>File：RwaBalancesController.java </p>
 * <p>Title: RwaBalancesController </p>
 * <p>Description:RwaBalancesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/rwaBalances")
@Api(tags = "RWA账户余额")
public class RwaBalancesController extends GenericController
{
    @Autowired(required = false)
    private RwaBalancesService rwaBalancesService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA账户余额", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaBalances>> data(@ModelAttribute ReqRwaBalancesPagination pagin) throws BusinessException
    {
        RwaBalances rwaBalancesQuery = new RwaBalances();
        BeanUtils.copyProperties(pagin, rwaBalancesQuery);
        rwaBalancesQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<RwaBalances> result = rwaBalancesService.search(pagin, rwaBalancesQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取RWA账户余额", httpMethod = "GET")
    public JsonMessage<RwaBalances> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaBalancesService.selectByPrimaryKey(id));
    }
}
