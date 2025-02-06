/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.controller.account.req.ReqAccountPagination;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.openim.entity.RegisterDefaultFriend;
import com.anyex.apps.openim.service.RegisterDefaultFriendService;

import com.anyex.apps.controller.openim.req.ReqRegisterDefaultFriend;
import com.anyex.apps.controller.openim.req.ReqRegisterDefaultFriendPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 注册默认好友 控制器
 * <p>File：RegisterDefaultFriendController.java </p>
 * <p>Title: RegisterDefaultFriendController </p>
 * <p>Description:RegisterDefaultFriendController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim/registerDefaultFriend")
@Api(tags = "注册默认好友")
public class RegisterDefaultFriendController extends GenericController
{
    @Autowired(required = false)
    private RegisterDefaultFriendService registerDefaultFriendService;

    @Autowired(required = false)
    AccountService accountService;

   /* @GetMapping(value = "/findBy")
    @RequiresPermissions("openim:registerDefaultFriend:data")
    @ApiOperation(value = "根据ID取注册默认好友", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, registerDefaultFriendService.selectByPrimaryKey(id));
    }*/

    @PostMapping(value = "/add")
    @RequiresPermissions("openim:registerDefaultFriends:operator")
    @ApiOperation(value = "添加默认好友", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage save(String ids) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        String id[] = ids.split(",");
        for (int i = 0; i < id.length; i++)
        {
            if(StringUtils.isEmpty(id[i])) continue;
            Account account = accountService.selectByPrimaryKey(Long.parseLong(id[i]));
            RegisterDefaultFriend f = registerDefaultFriendService.findByUserId(account.getUserId());
            if (null == f)
            {
                f = new RegisterDefaultFriend();
                f.setUserId(account.getUserId());
                f.setNickname(account.getAccountName());
                f.setFaceUrl(account.getHeadUrl());
                f.setId(SerialnoUtils.buildPrimaryKey());
                registerDefaultFriendService.insert(f);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("openim:registerDefaultFriends:data")
    @ApiOperation(value = "查询注册默认好友", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRegisterDefaultFriendPagination pagin) throws BusinessException
    {
        RegisterDefaultFriend entity = new RegisterDefaultFriend();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RegisterDefaultFriend> result = registerDefaultFriendService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/user")
    @RequiresPermissions("openim:registerDefaultFriends:data")
    @ApiOperation(value = "用户下拉列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Account>> data(@ModelAttribute ReqAccountPagination reqAccountPagination) throws BusinessException
    {
        //
        Account account = new Account();
        BeanUtils.copyProperties(reqAccountPagination, account);
        account.setStatus(0);
        //
        PaginateResult<Account> result = accountService.search(reqAccountPagination, account);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }


    @PostMapping(value = "/del")
    @RequiresPermissions("openim:registerDefaultFriends:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        registerDefaultFriendService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
