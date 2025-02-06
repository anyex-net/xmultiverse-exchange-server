/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.account.req.ReqAccountPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.openim.entity.NoticeAccount;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.openim.api.friend.req.DeleteFriendReq;
import com.anyex.openim.api.friend.req.GetPaginationFriendsReq;
import com.anyex.openim.api.friend.resp.FriendInfo;
import com.anyex.openim.api.friend.resp.GetPaginationFriendsResp;
import com.anyex.openim.api.user.req.GetUsersOnlineStatusReq;
import com.anyex.openim.api.user.resp.GetUsersOnlineStatusResp_SuccessResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * IM 用户管理
 */ 
@Slf4j
@RestController
@RequestMapping("/openim")
@Api(tags = "用户管理")
public class OpenImUserController extends GenericController
{

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Autowired(required = false)
    private AccountService accountService;

    @PostMapping(value = "/imuser/list")
    @RequiresPermissions("openim:imuser:data")
    @ApiOperation(value = "账户分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Account>> list(@ModelAttribute ReqAccountPagination pagin) throws BusinessException
    {
        Account account = new Account();
        BeanUtils.copyProperties(pagin, account);
        PaginateResult<Account> result = accountService.search(pagin, account);
        if (result.getRecords().size()>0)
        {
            List<String> userIds = result.getRecords().stream()
                    .map(Account::getUserId)
                    .collect(Collectors.toList());

            GetUsersOnlineStatusReq req1 = new GetUsersOnlineStatusReq();
            req1.setUserIDs(userIds);
            List<GetUsersOnlineStatusResp_SuccessResult> list = openImApiService.getUsersOnlineStatus(req1);
            list.forEach(item->{
                result.getRecords().stream()
                        .filter(userInfo -> userInfo.getUserId().equals(item.getUserID()))
                        .forEach(userInfo -> userInfo.setOnline(item.getStatus().equals("online")));
            });
        }

        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

   /* public JsonMessage<PaginateResult<UserInfo>> list(@ModelAttribute ReqAccountPagination pagin) throws BusinessException
    {
        GetPaginationUsersReq req = new GetPaginationUsersReq();
        req.getPagination().setPageNumber(pagin.getCurrent());
        req.getPagination().setShowNumber(pagin.getSize());
        GetPaginationUsersResp resp =openImApiService.getUsers(req);
        pagin.setTotal(resp.getTotal()*1L);
        PaginateResult<UserInfo> result = new PaginateResult<UserInfo>(pagin, resp.getUsers());
        if (result.getRecords().size()>0)
        {
            List<String> userIds = result.getRecords().stream()
                    .map(UserInfo::getUserID)
                    .collect(Collectors.toList());

            GetUsersOnlineStatusReq req1 = new GetUsersOnlineStatusReq();
            req1.setUserIDs(userIds);
            List<GetUsersOnlineStatusResp_SuccessResult> list = openImApiService.getUsersOnlineStatus(req1);
            list.forEach(item->{
                result.getRecords().stream()
                        .filter(userInfo -> userInfo.getUserID().equals(item.getUserID()))
                        .forEach(userInfo -> userInfo.setOnline(item.getStatus().equals("online")));
            });
        }
        return getJsonMessage(CommonEnums.SUCCESS,result);
    }*/

    @PostMapping(value = "/imuser/relationship")
    @RequiresPermissions("openim:imuser:operator")
    @ApiOperation(value = "单个账户好友关系列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<FriendInfo>> data(@ModelAttribute ReqAccountPagination pagin) throws BusinessException
    {
        GetPaginationFriendsReq req = new GetPaginationFriendsReq();
        req.setUserID(pagin.getUserId());
        req.getPagination().setPageNumber(pagin.getCurrent());
        req.getPagination().setShowNumber(pagin.getSize());
        GetPaginationFriendsResp resp = openImApiService.getFriendList(req);
        pagin.setTotal(resp.getTotal()*1L);
        PaginateResult<FriendInfo> result = new PaginateResult<FriendInfo>(pagin, resp.getFriendsInfo());
        return getJsonMessage(CommonEnums.SUCCESS,result);
    }

    @PostMapping(value = "/imuser/deleteFriend")
    @RequiresPermissions("openim:imuser:operator")
    @ApiOperation(value = "删除用户的好友", httpMethod = "POST")
    public JsonMessage deleteFriend(@Valid @ModelAttribute DeleteFriendReq req) throws BusinessException
    {
        openImApiService.deleteFriend(req);
        return getJsonMessage(CommonEnums.SUCCESS);
    }


    @PostMapping(value = "/imuser/forceLogout")
    @RequiresPermissions("openim:imuser:operator")
    @ApiOperation(value = "强制下线", httpMethod = "POST")
    @ApiImplicitParam(name = "userId", value = "用户userId", paramType = "form")
    public JsonMessage<PaginateResult<NoticeAccount>> userForceLogout(String userId) throws BusinessException
    {
        forceLogout(userId);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 强制退出登录 各个客户端
     * @param userId
     */
    private void forceLogout(String userId) {
        for (int i = 1; i <= 10; i++) {
            openImApiService.forceLogout(i, userId);
        }
    }

}
