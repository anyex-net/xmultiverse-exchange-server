/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.social.req.ReqMyPostPagination;
import com.anyex.apps.controller.social.resp.RespMyInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.social.entity.SnsFriend;
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.model.AccountInfoModel;
import com.anyex.apps.social.model.SnsPostModel;
import com.anyex.apps.social.model.SnsPostQueryModel;
import com.anyex.apps.social.model.UserPostNumStatisticsModel;
import com.anyex.apps.social.service.SnsFansService;
import com.anyex.apps.social.service.SnsFollowService;
import com.anyex.apps.social.service.SnsFriendService;
import com.anyex.apps.social.service.SnsPostService;
import com.anyex.apps.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author anyex
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.SOCIAL + "/mine")
@Api(tags = "我的主页")
public class MyHomeController extends GenericController {

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SnsPostService snsPostService;

    @Autowired(required = false)
    private SnsFollowService snsFollowService;

    @Autowired(required = false)
    private SnsFansService snsFansService;

    @Autowired(required = false)
    private SnsFriendService snsFriendService;


    @GetMapping(value = "/info")
    @ApiOperation(value = "我的信息", httpMethod = "GET")
    public JsonMessage<RespMyInfo> myInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        UserPostNumStatisticsModel postNumModel = snsPostService.getUserPostNumStatisticsModel(account.getUserId());
        RespMyInfo info = new RespMyInfo();
        info.setId(account.getId());
        info.setUserId(account.getUserId());
        info.setAccountName(account.getAccountName());
        info.setHeadUrl(account.getHeadUrl());
        info.setFollowNum(snsFollowService.cntFollow(account.getUserId()));
        info.setFansNum(snsFansService.cntFans(account.getUserId()));

        // 自己看自己全部的帖子数量
        info.setPostNum(postNumModel.getTotalAllPostNum());

        info.setFriendNum(snsFriendService.getFriendsCntByUserId(account.getUserId()));
        return this.getJsonMessage(CommonEnums.SUCCESS, info);
    }

    @GetMapping(value = "/posts")
    @ApiOperation(value = "我的帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> myPosts(@Validated @ModelAttribute ReqMyPostPagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        SnsPostQueryModel search = new SnsPostQueryModel();
        search.setUserId(account.getUserId());
        search.setPostTextContent(pagination.getKeywords());
        search.setViewerUserId(account.getUserId());
        PaginateResult<SnsPostModel> result = snsPostService.homePosts(pagination,search);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/follows")
    @ApiOperation(value = "我的关注", httpMethod = "GET")
    public JsonMessage<PaginateResult<AccountInfoModel>> myFollows(@Validated @ModelAttribute Pagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        PaginateResult<AccountInfoModel> result = snsFollowService.listFollows(pagination,account.getUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/fans")
    @ApiOperation(value = "我的粉丝", httpMethod = "GET")
    public JsonMessage<PaginateResult<AccountInfoModel>> myFans(@Validated @ModelAttribute Pagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        PaginateResult<AccountInfoModel> result = snsFansService.listFans(pagination,account.getUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/friends")
    @ApiOperation(value = "我的朋友", httpMethod = "GET")
    public JsonMessage<PaginateResult<AccountInfoModel>> myFriends(@Validated @ModelAttribute Pagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        SnsFriend search = new SnsFriend();
        search.setUserId(account.getUserId());
        PaginateResult<AccountInfoModel> result = snsFriendService.myFriends(pagination,search);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

}