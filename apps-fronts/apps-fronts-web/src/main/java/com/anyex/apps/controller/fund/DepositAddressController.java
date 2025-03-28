/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqDepositAddress;
import com.anyex.apps.controller.fund.req.ReqDepositAddressPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.fund.entity.DepositAddress;
import com.anyex.apps.fund.service.DepositAddressService;
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
 * 充值地址 控制器
 * <p>File：DepositAddressController.java </p>
 * <p>Title: DepositAddressController </p>
 * <p>Description:DepositAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/depositAddress")
@Api(tags = "充值地址")
public class DepositAddressController extends GenericController
{
    @Autowired(required = false)
    private DepositAddressService depositAddressService;

    @PostMapping(value = "/getDepositAddress")
    @ApiOperation(value = "获取充值地址", httpMethod = "POST")
    public JsonMessage<DepositAddress> getDepositAddress(@Validated @RequestBody ReqDepositAddress reqDepositAddress) throws BusinessException
    {
        DepositAddress depositAddressQuery = new DepositAddress();
        BeanUtils.copyProperties(depositAddressQuery, reqDepositAddress);
        depositAddressQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        // DepositAddress result = depositAddressService.selectOne(depositAddressQuery);
        // 先写死
        DepositAddress result = new DepositAddress();
        result.setUserId(OnLineUserUtils.getPrincipal().getId());
        result.setCurrency(reqDepositAddress.getCurrency());
        result.setBlockchain(reqDepositAddress.getBlockchain());
        result.setDepositAddress("0x123456");
        result.setRemark("先写死");
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询充值地址", httpMethod = "POST")
    public JsonMessage<PaginateResult<DepositAddress>> data(@Validated @RequestBody ReqDepositAddressPagination pagin) throws BusinessException
    {
        DepositAddress depositAddressQuery = new DepositAddress();
        BeanUtils.copyProperties(pagin, depositAddressQuery);
        depositAddressQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<DepositAddress> result = depositAddressService.search(pagin, depositAddressQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取充值地址", httpMethod = "GET")
    public JsonMessage<DepositAddress> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, depositAddressService.selectByPrimaryKey(id));
    }
}
