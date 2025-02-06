/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.social.req.*;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.social.entity.*;
import com.anyex.apps.social.model.SnsPostCommentModel;
import com.anyex.apps.social.service.*;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
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
@RequestMapping(GlobalConst.SOCIAL + "/post/comment")
@Api(tags = "帖子评论管理")
public class PostCommentController extends GenericController {

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SnsPostService snsPostService;

    @Autowired(required = false)
    private SnsPostLikeService snsPostLikeService;

    @Autowired(required = false)
    private SnsPostCommentService snsPostCommentService;

    @Autowired(required = false)
    private SnsPostCommentLikeService snsPostCommentLikeService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "帖子评论列表", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostCommentModel>> list(@Validated @ModelAttribute ReqCommentPagination req) throws BusinessException
    {
        if(req.getCommentId()!=null && req.getCommentId() <=0)
        {
            req.setCommentId(null);
        }
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
        PaginateResult<SnsPostCommentModel> result = snsPostCommentService.getComments(req,Long.parseLong(req.getPostId()),req.getCommentId(),account.getUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "发表评论", httpMethod = "POST")
    public JsonMessage add(@Validated @RequestBody ReqPostCommentAdd req) throws BusinessException
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
        SnsPost post  = snsPostService.selectByPrimaryKey(Long.parseLong(req.getPostId()));
        post.setCommentNum(post.getCommentNum() + 1);
        snsPostService.updateByPrimaryKey(post);

        SnsPostComment comment = new SnsPostComment();
        comment.setUserId(post.getUserId());
        comment.setPostId(Long.parseLong(req.getPostId()));
        comment.setCommentUserId(account.getUserId());
        comment.setCommentContent(req.getCommentContent());
        comment.setCommentLikeNum(0);
        comment.setReplyTo(req.getReplyTo());
        comment.setLng(account.getLng());
        comment.setLat(account.getLat());
        comment.setLat(account.getLat());
        comment.setIsRead(com.anyex.apps.utils.StringUtils.equalsAnyIgnoreCase(account.getUserId(),post.getUserId())?1: 0);
        comment.setCreateTime(System.currentTimeMillis());
        comment.setUpdateTime(System.currentTimeMillis());
        comment.setId(SerialnoUtils.buildPrimaryKey());
        comment.setRemark(req.getRemark());
        snsPostCommentService.insert(comment);

        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/del")
    @ApiOperation(value = "删除评论", httpMethod = "POST")
    public JsonMessage del(@Validated @RequestBody ReqPostCommentDelete req) throws BusinessException
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
        SnsPost post  = snsPostService.selectByPrimaryKey(req.getPostId());
        post.setCommentNum(post.getCommentNum() -1);
        snsPostService.updateByPrimaryKey(post);
        SnsPostComment comment = snsPostCommentService.selectByPrimaryKey(req.getPostCommentId());

        if(StringUtils.equalsIgnoreCase(account.getUserId(),comment.getCommentUserId())
        || StringUtils.equalsIgnoreCase(account.getUserId(),comment.getUserId()))
        {
            snsPostCommentService.remove(req.getPostCommentId());
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/like")
    @ApiOperation(value = "评论点赞/取消点赞操作", httpMethod = "POST")
    public JsonMessage<String> like(@Validated @RequestBody ReqPostCommentLike req) throws BusinessException
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

        SnsPostComment post = snsPostCommentService.selectByPrimaryKey(req.getPostCommentId());
        if(post == null)
        {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }


        SnsPostCommentLike entity = new SnsPostCommentLike();
        entity.setPostId(req.getPostId());
        entity.setPostCommentId(req.getPostCommentId());
        entity.setLikeUserId(account.getUserId());
        List<SnsPostCommentLike> list  = snsPostCommentLikeService.findList(entity);
        if(list.size() == 0)
        {
            post.setCommentLikeNum(post.getCommentLikeNum() +1);
            snsPostCommentService.updateByPrimaryKey(post);

            entity.setLng(account.getLng());
            entity.setLat(account.getLat());
            entity.setCreateTime(System.currentTimeMillis());
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setId(SerialnoUtils.buildPrimaryKey());
            snsPostCommentLikeService.insert(entity);
        }
        else
        {
            unlike(req);
        }

        /*SendMsgReq sendReq = new SendMsgReq();
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("content","我点赞了你的评率");

       //  sendReq.setRecvID(req.getUserId());
        sendReq.setSendID(account.getUserId());
        sendReq.setSenderNickname(account.getAccountName());
        sendReq.setSenderFaceURL(account.getHeadUrl());
        sendReq.setSenderPlatformID(1);
        sendReq.setContent(content);
        sendReq.setContentType(101);
        sendReq.setSessionType(1);
        openImApiService.sendMessage(sendReq);*/
        return this.getJsonMessage(CommonEnums.SUCCESS );
    }

   /* @PostMapping(value = "/unlike")
    @ApiOperation(value = "评论取消点赞操作", httpMethod = "POST")*/
    public JsonMessage<String> unlike(@Validated @RequestBody ReqPostCommentLike req) throws BusinessException
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

        SnsPostComment post = snsPostCommentService.selectByPrimaryKey(req.getPostCommentId());
        if(post == null)
        {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        SnsPostCommentLike entity = new SnsPostCommentLike();
        entity.setPostId(req.getPostId());
        entity.setPostCommentId(req.getPostCommentId());
        entity.setLikeUserId(account.getUserId());
        List<SnsPostCommentLike> list  = snsPostCommentLikeService.findList(entity);
        if(list.size() > 0)
        {
            post.setCommentLikeNum(post.getCommentLikeNum() -1);
            snsPostCommentService.updateByPrimaryKey(post);
            snsPostCommentLikeService.remove(list.get(0).getId());
        }

        return this.getJsonMessage(CommonEnums.SUCCESS);
    }
}
