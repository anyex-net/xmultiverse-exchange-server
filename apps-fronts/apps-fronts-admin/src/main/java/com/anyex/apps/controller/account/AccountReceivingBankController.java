/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.account.service.AccountReceivingBankService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountReceivingBankPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.account.entity.AccountReceivingBank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户收款银行表 控制器
 * <p>File：AccountReceivingBankController.java </p>
 * <p>Title: AccountReceivingBankController </p>
 * <p>Description:AccountReceivingBankController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountReceivingBank")
@Api(tags = "账户收款银行")
public class AccountReceivingBankController extends GenericController
{
    @Autowired(required = false)
    private AccountReceivingBankService accountReceivingBankService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountReceivingBank:data")
    @ApiOperation(value = "根据ID取账户收款银行", httpMethod = "GET")
    public JsonMessage<AccountReceivingBank> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountReceivingBankService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountReceivingBank:data")
    @ApiOperation(value = "查询账户收款银行", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountReceivingBank>> data(@Validated @ModelAttribute ReqAccountReceivingBankPagination reqAccountReceivingBankPagination) throws BusinessException
    {
        //
        AccountReceivingBank accountReceivingBank = new AccountReceivingBank();
        BeanUtils.copyProperties(reqAccountReceivingBankPagination, accountReceivingBank);
        //
        PaginateResult<AccountReceivingBank> result = accountReceivingBankService.search(reqAccountReceivingBankPagination, accountReceivingBank);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
