/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;


import com.anyex.apps.account.entity.AccountInviteRewards;
import com.anyex.apps.account.model.AccountInvitedRewardsDetailModel;
import com.anyex.apps.account.model.AccountInvitedStatisticsForAppModel;
import com.anyex.apps.account.service.AccountInviteRewardsService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountInviteDetail;
import com.anyex.apps.controller.account.req.ReqAccountInviteRewardsPagination;
import com.anyex.apps.controller.account.req.ReqAccountInviteRewardsStatistics;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 邀请返佣统计记录
 */
@Slf4j
@RestController
@RequestMapping("/account/accountInviteRewards")
@Api(tags = "账户邀请返佣")
public class AccountInviteRewardsController extends GenericController
{
    @Autowired(required = false)
    private AccountInviteRewardsService accountInviteRewardsService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询邀请返佣分级统计", httpMethod = "POST")
    public JsonMessage<AccountInvitedStatisticsForAppModel> data(@RequestBody ReqAccountInviteRewardsStatistics info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        if(info.getLevel() == 2 && info.getSecondAccountId() == null)
        {
            throw new BusinessException("请传入二级邀请账户id");
        }
        if(info.getLevel() == 3 && (info.getSecondAccountId() == null || info.getThirdAccountId() == null))
        {
            throw new BusinessException("请传入二级邀请账户id和三级账户id");
        }
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS,accountInviteRewardsService.getStatisticsItemsDetailForApp(info.getLevel(),principal.getId(),info.getSecondAccountId(),info.getThirdAccountId()));
        return json;
    }

    @PostMapping(value = "/total")
    @ApiOperation(value = "邀请返佣返佣合计", httpMethod = "POST")
    public JsonMessage<AccountInvitedStatisticsForAppModel> total() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        ReqAccountInviteRewardsStatistics info = new ReqAccountInviteRewardsStatistics();
        info.setLevel(1);
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS,accountInviteRewardsService.getStatisticsItemsDetailForApp(info.getLevel(),principal.getId(),info.getSecondAccountId(),info.getThirdAccountId()));
        return json;
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "查询邀请返佣列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountInviteRewards>> list(@Validated @RequestBody ReqAccountInviteRewardsPagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountInviteRewards entity = new AccountInviteRewards();
        entity.setRewardsAccountId(principal.getId());
        entity.setRewardsStatus(1);
        PaginateResult<AccountInviteRewards> result = accountInviteRewardsService.search(pagination, entity);
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS,result);
        return json;
    }

    @PostMapping(value = "/detail")
    @ApiOperation(value = "查询邀请返佣一级明细", httpMethod = "POST")
    public JsonMessage<AccountInvitedRewardsDetailModel> list(@Validated @RequestBody ReqAccountInviteDetail model) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS,accountInviteRewardsService.getFirstLevelDetailsForApp(principal.getId(),model.getFirstAccountId()));
        return json;
    }

}
