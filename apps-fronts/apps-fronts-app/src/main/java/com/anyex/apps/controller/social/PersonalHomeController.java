/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.social.req.ReqPersonPostPagination;
import com.anyex.apps.controller.social.req.ReqUserId;
import com.anyex.apps.controller.social.resp.RespPersonInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.social.entity.SnsFans;
import com.anyex.apps.social.entity.SnsFollow;
import com.anyex.apps.social.entity.SnsFriend;
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.model.SnsPostModel;
import com.anyex.apps.social.model.SnsPostQueryModel;
import com.anyex.apps.social.model.UserPostNumStatisticsModel;
import com.anyex.apps.social.service.SnsFansService;
import com.anyex.apps.social.service.SnsFollowService;
import com.anyex.apps.social.service.SnsFriendService;
import com.anyex.apps.social.service.SnsPostService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(GlobalConst.SOCIAL + "/personal")
@Api(tags = "个人主页")
public class PersonalHomeController extends GenericController {

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
    @ApiOperation(value = "个人信息", httpMethod = "GET")
    public JsonMessage<RespPersonInfo> info(@Validated @ModelAttribute ReqUserId req) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.findByUserId(req.getUserId());
        if (null != account && !account.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }

        Account me = accountService.selectByPrimaryKey(principal.getId());

        UserPostNumStatisticsModel postNumModel = snsPostService.getUserPostNumStatisticsModel(account.getUserId());
        RespPersonInfo info = new RespPersonInfo();
        info.setId(account.getId());
        info.setUserId(account.getUserId());
        info.setAccountName(account.getAccountName());
        info.setHeadUrl(account.getHeadUrl());
        info.setFollowNum(snsFollowService.cntFollow(account.getUserId()));
        info.setFansNum(snsFansService.cntFans(account.getUserId()));

        // 只能看到别人公开的数量
        info.setPostNum(postNumModel.getTotalPublicPostNum());
        info.setFriendNum(snsFriendService.getFriendsCntByUserId(account.getUserId()));


        // 我是否关注了他
        info.setIsMyFollow(false);
        SnsFollow follow = new SnsFollow();
        follow.setUserId(me.getUserId());
        follow.setFollowedUserId(req.getUserId());
        List<SnsFollow> followList = snsFollowService.findList(follow);
        if (followList.size()>0) {
            info.setIsMyFollow(true);
        }

        // 他是否我的粉丝
        info.setIsMyFans(false);
        SnsFans fans = new SnsFans();
        fans.setUserId(me.getUserId());
        fans.setFollowerUserId(req.getUserId());
        List<SnsFans> fansList = snsFansService.findList(fans);
        if (fansList.size()>0) {
           info.setIsMyFans(true);
        }

        // 他是否我的朋友
        info.setIsMyFriend(false);
        SnsFriend snsFriend = new SnsFriend();
        snsFriend.setUserId(req.getUserId());
        snsFriend.setFriendUserId(me.getUserId());
        List<SnsFriend> list = snsFriendService.findList(snsFriend);
        if(list.size()>0) {
            info.setIsMyFriend(true);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS, info);
    }

    @GetMapping(value = "/posts")
    @ApiOperation(value = "个人帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPost>> posts(@Validated @ModelAttribute ReqPersonPostPagination pagination) throws BusinessException
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
        search.setUserId(pagination.getUserId());
        search.setViewerUserId(account.getUserId());
        search.setPostTextContent(pagination.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.homePosts(pagination,search);
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/follow")
    @ApiOperation(value = "关注操作", httpMethod = "POST")
    public JsonMessage<String> follow(@Validated @RequestBody ReqUserId req) throws BusinessException
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
        if(StringUtils.equalsIgnoreCase(account.getUserId(),req.getUserId()))
        {
            throw new BusinessException(CommonEnums.FOLLOW_SELF_ERR);
        }
        SnsFollow follow = new SnsFollow();
        follow.setUserId(account.getUserId());
        follow.setFollowedUserId(req.getUserId());
        List<SnsFollow> followList = snsFollowService.findList(follow);
        if (followList.isEmpty()) {
            snsFollowService.follow(account.getUserId(),req.getUserId());
        }
        else
        {
            snsFollowService.unfollow(account.getUserId(),req.getUserId());
        }

        return this.getJsonMessage(CommonEnums.SUCCESS );
    }

    /*@PostMapping(value = "/unfollow")
    @ApiOperation(value = "关注取消操作", httpMethod = "POST")
    public JsonMessage<String> unfollow(@Validated @RequestBody ReqUserId req) throws BusinessException
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

        SnsFollow follow = new SnsFollow();
        follow.setUserId(account.getUserId());
        follow.setFollowedUserId(req.getUserId());
        List<SnsFollow> followList = snsFollowService.findList(follow);
        if (followList.isEmpty()) {
            snsFollowService.follow(account.getUserId(),req.getUserId());
        }
        else
        {
            snsFollowService.unfollow(account.getUserId(),req.getUserId());
        }

        return this.getJsonMessage(CommonEnums.SUCCESS);
    }*/
}