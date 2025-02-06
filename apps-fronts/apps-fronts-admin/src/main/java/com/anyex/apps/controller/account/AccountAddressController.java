/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.account.service.AccountAddressService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountAddressPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.account.entity.AccountAddress;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户收货地址 控制器
 * <p>File：AccountAddressController.java </p>
 * <p>Title: AccountAddressController </p>
 * <p>Description:AccountAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountAddress")
@Api(tags = "账户收货地址")
public class AccountAddressController extends GenericController
{
    @Autowired(required = false)
    private AccountAddressService accountAddressService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountAddress:data")
    @ApiOperation(value = "根据ID取账户地址", httpMethod = "GET")
    public JsonMessage<AccountAddress> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountAddressService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountAddress:data")
    @ApiOperation(value = "查询账户地址分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountAddress>> data(@Validated @ModelAttribute ReqAccountAddressPagination reqAccountAddressPagination) throws BusinessException
    {
        //
        AccountAddress accountAddress = new AccountAddress();
        BeanUtils.copyProperties(reqAccountAddressPagination, accountAddress);
        //
        PaginateResult<AccountAddress> result = accountAddressService.search(reqAccountAddressPagination, accountAddress);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
