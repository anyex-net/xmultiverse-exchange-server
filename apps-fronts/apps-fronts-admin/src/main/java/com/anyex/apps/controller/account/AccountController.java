/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.account.entity.Account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户 控制器
 * <p>File：AccountController.java </p>
 * <p>Title: AccountController </p>
 * <p>Description:AccountController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/account")
@Api(tags = "账户信息")
public class AccountController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "根据ID取账户", httpMethod = "GET")
    public JsonMessage<Account> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "查询账户分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Account>> data(@ModelAttribute ReqAccountPagination reqAccountPagination) throws BusinessException
    {
        //
        Account account = new Account();
        BeanUtils.copyProperties(reqAccountPagination, account);
        //
        PaginateResult<Account> result = accountService.search(reqAccountPagination, account);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
