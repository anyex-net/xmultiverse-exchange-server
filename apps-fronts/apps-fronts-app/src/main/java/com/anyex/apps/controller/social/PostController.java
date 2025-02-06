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
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.entity.SnsPostLike;
import com.anyex.apps.social.entity.SnsPostShare;
import com.anyex.apps.social.model.SnsPostCityQueryModel;
import com.anyex.apps.social.model.SnsPostLocationQueryModel;
import com.anyex.apps.social.model.SnsPostModel;
import com.anyex.apps.social.model.SnsPostQueryModel;
import com.anyex.apps.social.service.SnsPostLikeService;
import com.anyex.apps.social.service.SnsPostService;
import com.anyex.apps.social.service.SnsPostShareService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.openim.api.msg.req.SendMsgReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(GlobalConst.SOCIAL + "/post")
@Api(tags = "帖子管理")
public class PostController extends GenericController {

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SnsPostService snsPostService;

    @Autowired(required = false)
    private SnsPostLikeService snsPostLikeService;

    @Autowired(required = false)
    private SnsPostShareService snsPostShareService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/follow")
    @ApiOperation(value = "频道关注的帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> follow(@Validated @ModelAttribute ReqPublicPostPagination req) throws BusinessException
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
        search.setViewerUserId(account.getUserId());
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.followPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/recommend/public")
    @ApiOperation(value = "频道推荐的公开帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> recommendPublicPosts(@Validated @ModelAttribute ReqPublicPostPagination req) throws BusinessException
    {
        String userId = null;
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            userId = null;
        }
        else {
            Account account = accountService.selectByPrimaryKey(principal.getId());
            if (null != account && !account.verifySignature())
            {// 校验数据
                userId = null;
            }
            else
            {
                userId = account.getUserId();
            }
        }
        SnsPostQueryModel search = new SnsPostQueryModel();
        search.setViewerUserId(userId);
        search.setOpenness(1);
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.publicPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/recommend/anonymous")
    @ApiOperation(value = "频道推荐的匿名帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> recommendAnonymousPosts(@Validated @ModelAttribute ReqPublicPostPagination req) throws BusinessException
    {
        String userId = null;
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            userId = null;
        }
        else {
            Account account = accountService.selectByPrimaryKey(principal.getId());
            if (null != account && !account.verifySignature())
            {// 校验数据
                userId = null;
            }
            else
            {
                userId = account.getUserId();
            }
        }
        SnsPostQueryModel search = new SnsPostQueryModel();
        search.setViewerUserId(userId);
        search.setOpenness(0);
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.publicPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/location/public")
    @ApiOperation(value = "频道附近的公开帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> locationPublicPosts(@Validated @ModelAttribute ReqLocationPostPagination req) throws BusinessException
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
        SnsPostLocationQueryModel search = new SnsPostLocationQueryModel();
        search.setViewerUserId(account.getUserId());
        if(StringUtils.isEmpty(req.getViewerLat()) || StringUtils.isEmpty(req.getViewerLng()))
        {
            search.setViewerLat(req.getViewerLat());
            search.setViewerLng(req.getViewerLng());
        }
        else
        {
            if(StringUtils.isEmpty(account.getLat()) || StringUtils.isEmpty(account.getLng()))
            {
                throw new BusinessException(CommonEnums.ERROR_LOCATION);
            }
            search.setViewerLat(account.getLat());
            search.setViewerLng(account.getLng());
        }

        search.setOpenness(1);
        search.setDistance(20000);
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.locationPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }


    @GetMapping(value = "/location/anonymous")
    @ApiOperation(value = "频道附近的匿名帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> locationAnonymousPosts(@Validated @ModelAttribute ReqLocationPostPagination req) throws BusinessException
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
        SnsPostLocationQueryModel search = new SnsPostLocationQueryModel();
        search.setViewerUserId(account.getUserId());
        if(StringUtils.isEmpty(req.getViewerLat()) || StringUtils.isEmpty(req.getViewerLng()))
        {
            search.setViewerLat(req.getViewerLat());
            search.setViewerLng(req.getViewerLng());
        }
        else
        {
            if(StringUtils.isEmpty(account.getLat()) || StringUtils.isEmpty(account.getLng()))
            {
                throw new BusinessException(CommonEnums.ERROR_LOCATION);
            }
            search.setViewerLat(account.getLat());
            search.setViewerLng(account.getLng());
        }
        search.setOpenness(0);
        search.setDistance(20000);
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.locationPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/city/public")
    @ApiOperation(value = "同城公开帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> cityPublicPosts(@Validated @ModelAttribute ReqCityPostPagination req) throws BusinessException
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
        SnsPostCityQueryModel search = new SnsPostCityQueryModel();
        search.setViewerUserId(account.getUserId());
        if(StringUtils.isNotEmpty(req.getCity()))
        {
            search.setCity(req.getCity());
        }
        else
        {
            if(StringUtils.isEmpty(account.getCity()))
            {
                throw new BusinessException(CommonEnums.ERROR_LOCATION);
            }
            search.setCity(account.getCity());
        }

        search.setOpenness(1);
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.cityPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/city/anonymous")
    @ApiOperation(value = "同城匿名的帖子", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> cityAnonymousPosts(@Validated @ModelAttribute ReqCityPostPagination req) throws BusinessException
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
        SnsPostCityQueryModel search = new SnsPostCityQueryModel();
        search.setViewerUserId(account.getUserId());
        if(StringUtils.isNotEmpty(req.getCity()))
        {
            search.setCity(req.getCity());
        }
        else
        {
            if(StringUtils.isEmpty(account.getCity()))
            {
                throw new BusinessException(CommonEnums.ERROR_LOCATION);
            }
            search.setCity(account.getCity());
        }
        search.setOpenness(0);
        search.setPostTextContent(req.getKeywords());
        PaginateResult<SnsPostModel> result = snsPostService.cityPosts(req,search);
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0 && req.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }


    @GetMapping(value = "/detail")
    @ApiOperation(value = "帖子详情", httpMethod = "GET")
    public JsonMessage<SnsPostModel> detail(@Validated @ModelAttribute ReqPostId req) throws BusinessException
    {
        String userId = null;
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            userId = null;
        }
        else {
            Account account = accountService.selectByPrimaryKey(principal.getId());
            if (null != account && !account.verifySignature())
            {// 校验数据
              userId = null;
            }
            else
            {
                userId = account.getUserId();
            }
        }

        SnsPostModel result = snsPostService.findById(Long.parseLong(req.getPostId()),userId);
        // (value = "0公开、1仅限好友、2仅限粉丝、3仅限自己")
        if(result == null)
        {
            return this.getJsonMessage(CommonEnums.NO_AUTH_OR_DELETED);
        }
        if(result.getViewer() == 3
            || result.getViewer() == 2 && result.getIsFollow()==0
                || result.getViewer() == 1 && result.getIsFriend()==0
        )
        {
            if(result != null && !StringUtils.equalsAnyIgnoreCase(userId,result.getUserId()))
            {
                result = null;
            }
        }
        if(result == null)
        {
            return this.getJsonMessage(CommonEnums.NO_AUTH_OR_DELETED);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/like")
    @ApiOperation(value = "帖子点赞/取消点赞操作", httpMethod = "POST")
    public JsonMessage<String> like(@Validated @RequestBody ReqPostId req) throws BusinessException
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

        SnsPost post = snsPostService.selectByPrimaryKey(Long.parseLong(req.getPostId()));
        if(post == null)
        {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        SnsPostLike entity = new SnsPostLike();
        entity.setPostId(Long.parseLong(req.getPostId()));
        entity.setUserId(post.getUserId());
        entity.setLikeUserId(account.getUserId());
        List<SnsPostLike> list  = snsPostLikeService.findList(entity);
        if(list.size() == 0)
        {
            post.setLikeNum(post.getLikeNum() + 1);
            snsPostService.updateByPrimaryKey(post);

            entity.setLng(account.getLng());
            entity.setLat(account.getLat());
            entity.setCreateTime(System.currentTimeMillis());
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setId(SerialnoUtils.buildPrimaryKey());
            entity.setIsRead(StringUtils.equalsAnyIgnoreCase(account.getUserId(),post.getUserId())?1: 0);
            entity.setRemark(req.getRemark());
            snsPostLikeService.insert(entity);

            /*SendMsgReq sendReq = new SendMsgReq();
            Map<String, Object> content = new HashMap<String, Object>();
            content.put("content","我点赞了你的博文"+req.getPostId());

            sendReq.setRecvID(post.getUserId());
            sendReq.setSendID(account.getUserId());
            sendReq.setSenderNickname(account.getAccountName());
            sendReq.setSenderFaceURL(account.getHeadUrl());
            sendReq.setSenderPlatformID(1);
            sendReq.setContent(content);
            sendReq.setContentType(101);
            sendReq.setSessionType(1);
            openImApiService.sendMessage(sendReq);*/
        }
        else {
            unlike(req);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS );
    }

    /*@PostMapping(value = "/unlike")
    @ApiOperation(value = "帖子点赞取消操作", httpMethod = "POST")*/
    public JsonMessage<String> unlike(@Validated @RequestBody ReqPostId req) throws BusinessException
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

        SnsPost post = snsPostService.selectByPrimaryKey(Long.parseLong(req.getPostId()));
        if(post == null)
        {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        SnsPostLike entity = new SnsPostLike();
        entity.setPostId(Long.parseLong(req.getPostId()));
        entity.setUserId(post.getUserId());
        entity.setLikeUserId(account.getUserId());
        List<SnsPostLike> list = snsPostLikeService.findList(entity);
        if(list.size() > 0)
        {
            post.setLikeNum(post.getLikeNum() -1);
            snsPostService.updateByPrimaryKey(post);

            snsPostLikeService.remove(list.get(0).getId());

            /*SendMsgReq sendReq = new SendMsgReq();
            Map<String, Object> content = new HashMap<String, Object>();
            content.put("content","我取消了对你的博文点赞"+req.getPostId());

            sendReq.setRecvID(post.getUserId());
            sendReq.setSendID(account.getUserId());
            sendReq.setSenderNickname(account.getAccountName());
            sendReq.setSenderFaceURL(account.getHeadUrl());
            sendReq.setSenderPlatformID(1);
            sendReq.setContent(content);
            sendReq.setContentType(101);
            sendReq.setSessionType(1);
            openImApiService.sendMessage(sendReq);*/
        }
        else {
            like(req);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/share")
    @ApiOperation(value = "帖子分享操作", httpMethod = "POST")
    public JsonMessage<String> share(@Validated @RequestBody ReqShare req) throws BusinessException
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

        SnsPost post = snsPostService.selectByPrimaryKey(req.getPostId());
        if(post == null)
        {
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }

        if(req.getUserIds() == null || req.getUserIds().length == 0)
        {
            post.setShareNum(post.getShareNum() + 1);
            snsPostService.updateByPrimaryKey(post);
        }

        for(String userId : req.getUserIds())
        {
            post.setShareNum(post.getShareNum() + 1);
            snsPostService.updateByPrimaryKey(post);

            SnsPostShare entity = new SnsPostShare();
            entity.setUserId(account.getUserId());
            entity.setPostId(post.getId());
            entity.setShareUserId(userId);
            entity.setLng(account.getLng());
            entity.setLat(account.getLat());
            entity.setCreateTime(System.currentTimeMillis());
            entity.setUpdateTime(System.currentTimeMillis());
            entity.setId(SerialnoUtils.buildPrimaryKey());
            snsPostShareService.insert(entity);

           /* SendMsgReq sendReq = new SendMsgReq();
            Map<String, Object> content = new HashMap<String, Object>();
            content.put("content","我分享给你一篇博文，请阅读"+req.getPostId());

            sendReq.setRecvID(userId);
            sendReq.setSendID(account.getUserId());
            sendReq.setSenderNickname(account.getAccountName());
            sendReq.setSenderFaceURL(account.getHeadUrl());
            sendReq.setSenderPlatformID(1);
            sendReq.setContent(content);
            sendReq.setContentType(101);
            sendReq.setSessionType(1);
            try {
                openImApiService.sendMessage(sendReq);
            } catch (Exception e)
            {
                log.error("send message error",e);
            }*/

        }

        return this.getJsonMessage(CommonEnums.SUCCESS );
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "发表帖子", httpMethod = "POST")
    public JsonMessage<String> add(@Validated @RequestBody ReqPostAdd req) throws BusinessException
    {
        if(StringUtils.isEmpty(req.getPostImageUrl()) && StringUtils.isEmpty(req.getPostTextContent()))
        {
            throw new BusinessException(9999,"Please input content or Upload images");
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

        SnsPost entity = new SnsPost();
        entity.setUserId(account.getUserId());
        entity.setPostTextContent(req.getPostTextContent());
        entity.setPostImageUrl(req.getPostImageUrl());
        entity.setOpenness(req.getOpenness());
        entity.setCity(req.getCity());
        entity.setLng(req.getLng());
        entity.setLat(req.getLat());
        entity.setFavoriteNum(0);
        entity.setLikeNum(0);
        entity.setViewer(req.getViewer());
        entity.setCommentNum(0);
        entity.setShareNum(0);
        entity.setRemark("");
        entity.setViewNum(0);
        entity.setCreateTime(System.currentTimeMillis());
        entity.setUpdateTime(System.currentTimeMillis());
        entity.setId(SerialnoUtils.buildPrimaryKey());
        snsPostService.insert(entity);

       /* SendMsgReq sendReq = new SendMsgReq();
        Map<String, Object> content = new HashMap<String, Object>();
        content.put("content","我发表了博文，请你阅读"+entity.getId());
        sendReq.setSendID(account.getUserId());
        sendReq.setSenderNickname(account.getAccountName());
        sendReq.setSenderFaceURL(account.getHeadUrl());
        sendReq.setSenderPlatformID(1);
        sendReq.setContent(content);
        sendReq.setContentType(101);
        sendReq.setSessionType(1);
        if(req.getUserIds()!=null) {
            for (String uid : req.getUserIds()) {
                sendReq.setRecvID(uid);
                openImApiService.sendMessage(sendReq);
            }
        }*/

        return this.getJsonMessage(CommonEnums.SUCCESS );
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改帖子", httpMethod = "POST")
    public JsonMessage<String> update(@Validated @RequestBody ReqPostUpdate req) throws BusinessException
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

        SnsPost entity = new SnsPost();
        entity.setId(req.getId());
        entity.setUserId(account.getUserId());
        entity.setPostTextContent(req.getPostTextContent());
        entity.setPostImageUrl(req.getPostImageUrl());
        entity.setOpenness(req.getOpenness());
        /*entity.setCity(req.getCity());
        entity.setLng(req.getLng());
        entity.setLat(req.getLat());*/
        entity.setViewer(req.getViewer());
        entity.setUpdateTime(System.currentTimeMillis());
        snsPostService.updateByPrimaryKeySelective(entity);


        return this.getJsonMessage(CommonEnums.SUCCESS );
    }

    @PostMapping(value = "/del")
    @ApiOperation(value = "删除帖子", httpMethod = "POST")
    public JsonMessage del(@Validated @RequestBody ReqPostId req) throws BusinessException
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
        // 删除帖子
        snsPostService.remove(Long.valueOf(req.getPostId()));
        // 删除帖子相关联的点赞、分享、评论等数据

        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

}
