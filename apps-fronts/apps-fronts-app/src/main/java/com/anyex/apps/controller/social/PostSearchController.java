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
import com.anyex.apps.model.Pagination;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.entity.SnsPostLike;
import com.anyex.apps.social.entity.SnsPostShare;
import com.anyex.apps.social.model.*;
import com.anyex.apps.social.service.SnsPostLikeService;
import com.anyex.apps.social.service.SnsPostService;
import com.anyex.apps.social.service.SnsPostShareService;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(GlobalConst.SOCIAL)
@Api(tags = "搜索管理")
public class PostSearchController extends GenericController {

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


    @GetMapping(value = "/post/search")
    @ApiOperation(value = "帖子搜索", httpMethod = "GET")
    public JsonMessage<PaginateResult<SnsPostModel>> searchPosts(@Validated @ModelAttribute ReqSearchPostPagination req) throws BusinessException
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
        if(StringUtils.isNotEmpty(req.getKeywords()) && result.getRecords().size()>0) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", req.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    /**
     * 首页热门搜索排行榜
     */
    @GetMapping("/post/search/hot")
    @ApiOperation(value = "搜索热词", httpMethod = "GET")
    public  JsonMessage<List<String>>  rankingList() {
        List<String> rankingList = new ArrayList<>();
        try {
            Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores("RANKING_LIST", 0, 5);
            typedTuples.forEach(p -> {
                //key
                String value = p.getValue()+"";
                //value
                // Double score = p.getScore();
                rankingList.add(value);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return getJsonMessage(CommonEnums.SUCCESS, rankingList);
    }


    /**
     * 首页热门搜索排行榜
     */
    @GetMapping("/post/search/hotkeys")
    @ApiOperation(value = "搜索热词", httpMethod = "GET")
    public  JsonMessage<List<String>>  rankingkeysList(@Validated @ModelAttribute ReqSearchPostPagination req) {
        List<String> rankingList = new ArrayList<>();
        try {
            Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores("RANKING_LIST", 0, 1000);
            typedTuples.forEach(p -> {
                //key
                String value = p.getValue()+"";
                //value
                // Double score = p.getScore();
                if(StringUtils.startsWithIgnoreCase(value, req.getKeywords())) {
                    rankingList.add(value);
                }
            });

          /*  // 获取有序集合的所有成员和分数
            Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().rangeWithScores("RANKING_LIST", 0, -1);

            // 筛选出以"aaa"开头的成员
            Map<String, Double> filteredMembers = new LinkedHashMap<>();
            for (ZSetOperations.TypedTuple<String> tuple : tuples) {
                String member = tuple.getValue();
                if (member.startsWith(req.getKeywords())) {
                    filteredMembers.put(member, tuple.getScore());
                }
            }

            // 按照分数降序排序
            List<Map.Entry<String, Double>> entryList = new ArrayList<>(filteredMembers.entrySet());
            entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            for(Map.Entry<String, Double> e : entryList)
            {
                rankingList.add(e.getKey());
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }
        return getJsonMessage(CommonEnums.SUCCESS, rankingList);
    }


    @GetMapping(value = "/account/search")
    @ApiOperation(value = "用户搜索", httpMethod = "GET")
    public JsonMessage<PaginateResult<AccountInfoModel>> myFollows(@Validated @ModelAttribute ReqUserSearchPagination pagination) throws BusinessException
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
        PaginateResult<AccountInfoModel> result = accountService.findSocialList(pagination,pagination.getKeywords(),account.getUserId());
        if(StringUtils.isNotEmpty(pagination.getKeywords()) && result.getRecords().size()>0 && pagination.getCurrent() == 1) {
            redisTemplate.opsForZSet().incrementScore("RANKING_LIST", pagination.getKeywords(), 1);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }


}