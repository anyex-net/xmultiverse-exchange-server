/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.account.entity.AccountAddress;
import com.anyex.apps.account.service.AccountAddressService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountAddress;
import com.anyex.apps.controller.account.req.ReqAccountAddressPagination;
import com.anyex.apps.controller.common.req.ReqIdParam;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Slf4j
@RestController
@RequestMapping("/account/accountAddress")
@Api(tags = "账户收货地址")
public class AccountAddressController extends GenericController
{
    @Autowired(required = false)
    private AccountAddressService accountAddressService;

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取账户地址", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "业务记录唯一Id", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<AccountAddress> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        AccountAddress accountAddressDB = accountAddressService.selectByPrimaryKey(id);
        if(null == accountAddressDB || principal.getId().longValue() != accountAddressDB.getAccountId().longValue())
        {
            log.error("非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountAddressDB);
    }

    @PostMapping(value = "/save")
    @ApiOperation(value = "新增或更新账户地址", httpMethod = "POST")
    public JsonMessage save(@RequestBody ReqAccountAddress info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            if(info.getPrime()) {
                AccountAddress accountAddressPrime = new AccountAddress();
                accountAddressPrime.setAccountId(principal.getId());
                accountAddressPrime.setPrime(true);
                List<AccountAddress> listAccountAddressPrime = accountAddressService.findList(accountAddressPrime);
                if(null != listAccountAddressPrime && listAccountAddressPrime.size() > 0){
                    log.error("已经存在默认收货地址，不能重复新增");
                    throw new BusinessException(CommonEnums.ERROR_DB_UNIQUE_ERROR);
                }
            }
            //
            AccountAddress accountAddress = new AccountAddress();
            BeanUtils.copyProperties(info, accountAddress);
            accountAddress.setAccountId(principal.getId());
            accountAddress.setCreateTime(System.currentTimeMillis());
            accountAddress.setUpdateTime(System.currentTimeMillis());
            //
            log.info("accountAddress:{}", accountAddress);
            if(null == info.getId()){
                accountAddressService.insert(accountAddress);
            } else {
                AccountAddress accountAddressDB = accountAddressService.selectByPrimaryKey(info.getId());
                if(null == accountAddressDB || principal.getId().longValue() != accountAddressDB.getAccountId().longValue())
                {
                    log.error("非法请求");
                    throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                }
                accountAddressService.updateByPrimaryKey(accountAddress);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询账户地址分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountAddress>> data(@Validated @RequestBody ReqAccountAddressPagination reqAccountAddressPagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        AccountAddress accountAddress = new AccountAddress();
        BeanUtils.copyProperties(reqAccountAddressPagination, accountAddress);
        accountAddress.setAccountId(principal.getId());
        //
        PaginateResult<AccountAddress> result = accountAddressService.search(reqAccountAddressPagination, accountAddress);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    public JsonMessage del(@RequestBody ReqIdParam reqIdParam) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        AccountAddress accountAddressDB = accountAddressService.selectByPrimaryKey(reqIdParam.getId());
        if(null == accountAddressDB || principal.getId().longValue() != accountAddressDB.getAccountId().longValue())
        {
            log.error("非法请求");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        accountAddressService.remove(reqIdParam.getId());
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
