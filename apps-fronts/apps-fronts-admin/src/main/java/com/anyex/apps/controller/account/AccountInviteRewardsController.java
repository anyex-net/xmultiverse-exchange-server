/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.model.*;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.controller.account.req.ReqAccountPagination;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.account.entity.AccountInviteRewards;
import com.anyex.apps.account.service.AccountInviteRewardsService;

import com.anyex.apps.controller.account.req.ReqAccountInviteRewardsPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 账户邀请奖励表 控制器
 * <p>File：AccountInviteRewardsController.java </p>
 * <p>Title: AccountInviteRewardsController </p>
 * <p>Description:AccountInviteRewardsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/accountInviteRewards")
@Api(tags = "账户邀请奖励(旧)")
public class AccountInviteRewardsController extends GenericController
{
    @Autowired(required = false)
    private AccountInviteRewardsService accountInviteRewardsService;

    @Autowired(required = false)
    private AccountService accountService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "根据ID取账户邀请奖励", httpMethod = "GET")
    public JsonMessage<AccountInviteRewards> findBy(Long id) throws BusinessException
    {
        if (null == id){
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountInviteRewardsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "查询账户邀请奖励", httpMethod = "POST")
    public JsonMessage<AccountInviteRewards> data(@ModelAttribute ReqAccountInviteRewardsPagination pagin) throws BusinessException
    {
        AccountInviteRewards entity = new AccountInviteRewards();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountInviteRewards> result = accountInviteRewardsService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/data/rewards/statistics")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "佣金记录统计", httpMethod = "GET")
    public JsonMessage<AccountRewardsStatisticsModel> statisticsRewards() throws BusinessException
    {
        AccountRewardsStatisticsModel result = accountInviteRewardsService.getStatisticsModel();
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/data/rewards")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "佣金记录列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountRewardsItemModel>> dataRewards(@Validated @ModelAttribute ReqAccountPagination pagin) throws BusinessException
    {
        Account entity = new Account();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountRewardsItemModel> result = accountInviteRewardsService.getStatisticsItems(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/data/rewards/detail")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "佣金记录明细", httpMethod = "GET")
    public JsonMessage<AccountRewardsItemDetailModel> statisticsRewardsDetail(Long id) throws BusinessException
    {
        AccountRewardsItemDetailModel result = accountInviteRewardsService.getStatisticsItemsDetail(id);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/data/account/statistics")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "邀请记录统计", httpMethod = "GET")
    public JsonMessage<AccountInvitedStatisticsModel> statisticsAccountStatistics() throws BusinessException
    {
        AccountInvitedStatisticsModel result = accountService.getInvitedStatistics();
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/data/account")
    @RequiresPermissions("account:accountInviteRewards:data")
    @ApiOperation(value = "邀请记录列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountInvitedModel> > dataAccounts(@ModelAttribute ReqAccountPagination pagin) throws BusinessException
    {
        AccountInvitedModel entity = new AccountInvitedModel();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AccountInvitedModel> result = accountService.getInvitedAccount(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

}
