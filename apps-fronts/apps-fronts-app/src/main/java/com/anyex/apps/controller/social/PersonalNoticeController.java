/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.social.req.ReqPublicModel;
import com.anyex.apps.controller.social.resp.RespNoticeInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.social.entity.*;
import com.anyex.apps.social.model.SnsPostCommentModel;
import com.anyex.apps.social.model.SnsPostLikeModel;
import com.anyex.apps.social.service.*;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(GlobalConst.SOCIAL + "/personal/notice")
@Api(tags = "个人通知")
public class PersonalNoticeController extends GenericController {

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SnsFansService snsFansService;

    @Autowired(required = false)
    private SnsPostLikeService snsPostLikeService;

    @Autowired(required = false)
    private SnsPostCommentService snsPostCommentService;


    @GetMapping(value = "/info")
    @ApiOperation(value = "通知信息", httpMethod = "GET")
    public JsonMessage<RespNoticeInfo> info(@ModelAttribute ReqPublicModel req) throws BusinessException
    {
        RespNoticeInfo info = new RespNoticeInfo();
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

        Account me = accountService.selectByPrimaryKey(principal.getId());
        // 数量
        // 新粉丝数量
        SnsFans fans = new SnsFans();
        fans.setUserId(me.getUserId());
        fans.setIsRead(0);
        List<SnsFans> fansList = snsFansService.findList(fans);
        info.setCountFans(fansList.size());
        // 新评论数量
        List<SnsPostCommentModel> commentslist = snsPostCommentService.getAccountCommentsUnRead(me.getUserId().toString());
        info.setCountComments(commentslist.size());

        // 新点赞数量
        List<SnsPostLikeModel> paginateResult = snsPostLikeService.findListForRead(me.getUserId().toString(),false);
        info.setCountLikes(paginateResult.size());

        info.setCountAll(info.getCountComments()+info.getCountFans()+info.getCountLikes());

        return this.getJsonMessage(CommonEnums.SUCCESS,info);
    }

    @PostMapping(value = "/fans/set/read")
    @ApiOperation(value = "新粉丝已读", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsPost>> posts(@RequestBody ReqPublicModel userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account me = accountService.selectByPrimaryKey(principal.getId());
        SnsFans fans = new SnsFans();
        fans.setUserId(me.getUserId());
        fans.setIsRead(0);
        List<SnsFans> fansList = snsFansService.findList(fans);
        for (SnsFans snsFans : fansList)
        {
            snsFans.setIsRead(1);
        }
        if(fansList.size()>0)
        snsFansService.updateBatch(fansList);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/comments/unread/list")
    @ApiOperation(value = "未读评论", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostCommentModel>> commentsUnRead(@Validated @ModelAttribute Pagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account me = accountService.selectByPrimaryKey(principal.getId());
        PaginateResult<SnsPostCommentModel> paginateResult = snsPostCommentService.getAccountComments(pagination,me.getUserId().toString(),null);
        return this.getJsonMessage(CommonEnums.SUCCESS,paginateResult);
    }

    @PostMapping(value = "/comments/set/read")
    @ApiOperation(value = "评论已读", httpMethod = "POST")
    public JsonMessage<String> commentsRead(@RequestBody ReqPublicModel userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account me = accountService.selectByPrimaryKey(principal.getId());
        List<SnsPostCommentModel> paginateResult = snsPostCommentService.getAccountCommentsUnRead(me.getUserId().toString());
        SnsPostComment entity = null;
        for (SnsPostCommentModel snsPostCommentModel : paginateResult)
        {
            entity = new SnsPostComment();
            entity.setId(snsPostCommentModel.getId());
            entity.setIsRead(1);
            snsPostCommentService.updateByPrimaryKeySelective(entity);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,"ok");
    }

    @GetMapping(value = "/postlike/unread/list")
    @ApiOperation(value = "未读点赞", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostLikeModel>> likesUnRead(@Validated @ModelAttribute Pagination pagination) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account me = accountService.selectByPrimaryKey(principal.getId());
        PaginateResult<SnsPostLikeModel> paginateResult = snsPostLikeService.findListForRead(pagination,me.getUserId().toString(),null);
        return this.getJsonMessage(CommonEnums.SUCCESS,paginateResult);
    }

    @PostMapping(value = "/postlike/set/read")
    @ApiOperation(value = "点赞已读", httpMethod = "POST")
    public JsonMessage<String> likesRead(@RequestBody ReqPublicModel userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account me = accountService.selectByPrimaryKey(principal.getId());
        SnsPostLike entity;
        List<SnsPostLikeModel> paginateResult = snsPostLikeService.findListForRead(me.getUserId().toString(),false);
        for (SnsPostLikeModel snsPostLikeModel :paginateResult)
        {
            entity = new SnsPostLike();
            entity.setId(snsPostLikeModel.getLikeId());
            entity.setIsRead(1);
            snsPostLikeService.updateByPrimaryKeySelective(entity);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,"ok");
    }

    @PostMapping(value = "/clean")
    @ApiOperation(value = "一键清除消息", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsPost>> clean(@RequestBody ReqPublicModel userId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account me = accountService.selectByPrimaryKey(principal.getId());
        // 粉丝
        SnsFans fans = new SnsFans();
        fans.setUserId(me.getUserId());
        fans.setIsRead(0);
        List<SnsFans> fansList = snsFansService.findList(fans);
        for (SnsFans snsFans : fansList)
        {
            snsFans.setIsRead(1);
        }
        if(fansList.size()>0)
        snsFansService.updateBatch(fansList);
        SnsPostLike entity;
        List<SnsPostLikeModel> paginateResult = snsPostLikeService.findListForRead(me.getUserId().toString(),false);
        for (SnsPostLikeModel snsPostLikeModel :paginateResult)
        {
            entity = new SnsPostLike();
            entity.setId(snsPostLikeModel.getLikeId());
            entity.setIsRead(1);
            snsPostLikeService.updateByPrimaryKeySelective(entity);
        }
        List<SnsPostCommentModel> accountCommentsUnRead = snsPostCommentService.getAccountCommentsUnRead(me.getUserId().toString());
        SnsPostComment comment = null;
        for (SnsPostCommentModel snsPostCommentModel : accountCommentsUnRead)
        {
            comment = new SnsPostComment();
            comment.setId(snsPostCommentModel.getId());
            comment.setIsRead(1);
            snsPostCommentService.updateByPrimaryKeySelective(comment);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }


}
