/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.social.entity.SnsFans;
import com.anyex.apps.social.entity.SnsFriend;
import com.anyex.apps.social.model.AccountInfoModel;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.openim.api.friend.req.DeleteFriendReq;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsFollow;
import com.anyex.apps.social.mapper.SnsFollowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 社交关注(我关注的) 服务实现类
 * <p>File：SnsFollowServiceImpl.java </p>
 * <p>Title: SnsFollowServiceImpl </p>
 * <p>Description:SnsFollowServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
@Slf4j
public class SnsFollowServiceImpl extends GenericServiceImpl<SnsFollow> implements SnsFollowService
{
    protected SnsFollowMapper snsFollowMapper;

    @Autowired
    SnsFansService snsFansService;

    @Autowired
    SnsFriendService snsFriendService;

    @Autowired
    OpenImApiService openImApiService;

    @Autowired(required = false)
    public SnsFollowServiceImpl(SnsFollowMapper snsFollowMapper)
    {
        super(snsFollowMapper);
        this.snsFollowMapper = snsFollowMapper;
    }

    @Override
    public Integer cntFollow(String userId) {
        return snsFollowMapper.cntFollow(userId);
    }

    @Override
    public PaginateResult<AccountInfoModel> listFollows(Pagination pagin, String userId) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<AccountInfoModel> pageInfo = PageInfo.of(snsFollowMapper.listFollows(userId));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void follow(String userId, String friendUserId) {
        // 新增关注信息
        SnsFollow follow = new SnsFollow();
        follow.setUserId(userId);
        follow.setFollowedUserId(friendUserId);
        List<SnsFollow> followList = snsFollowMapper.findList(follow);
        if (followList.isEmpty()) {
            follow.setUpdateTime(System.currentTimeMillis());
            follow.setCreateTime(System.currentTimeMillis());
            follow.setId(SerialnoUtils.buildPrimaryKey());
            snsFollowMapper.insert(follow);
        }

        // 新增粉丝信息
        SnsFans fans = new SnsFans();
        fans.setUserId(friendUserId);
        fans.setFollowerUserId(userId);
        List<SnsFans> fansList = snsFansService.findList(fans);
        if (fansList.isEmpty()) {
            fans.setIsRead(0);
            fans.setUpdateTime(System.currentTimeMillis());
            fans.setCreateTime(System.currentTimeMillis());
            fans.setId(SerialnoUtils.buildPrimaryKey());
            snsFansService.insert(fans);
        }

        // 检测互关 如果反向也关注了
        follow = new SnsFollow();
        follow.setUserId(friendUserId);
        follow.setFollowedUserId(userId);
        followList = snsFollowMapper.findList(follow);
        if (followList.size()>0) {
            // 互增好友信息
            SnsFriend snsFriend = new SnsFriend();
            snsFriend.setUserId(userId);
            snsFriend.setFriendUserId(friendUserId);
            List<SnsFriend> list = snsFriendService.findList(snsFriend);
            if (list.isEmpty()) {
                snsFriend.setUpdateTime(System.currentTimeMillis());
                snsFriend.setCreateTime(System.currentTimeMillis());
                snsFriend.setId(SerialnoUtils.buildPrimaryKey());
                snsFriendService.insert(snsFriend);
            }

            snsFriend = new SnsFriend();
            snsFriend.setUserId(friendUserId);
            snsFriend.setFriendUserId(userId);
            list = snsFriendService.findList(snsFriend);
            if (list.isEmpty()) {
                snsFriend.setUpdateTime(System.currentTimeMillis());
                snsFriend.setCreateTime(System.currentTimeMillis());
                snsFriend.setId(SerialnoUtils.buildPrimaryKey());
                snsFriendService.insert(snsFriend);
            }

            // 接口新增好友关系 通过接口导入双向好友关系
            List<String> friends = new ArrayList<String>();
            friends.add(friendUserId);
            try{
                openImApiService.importFriends(userId,friends);
            }catch (BusinessException e)
            {
                log.error("新增好友关系失败：userId:{},friendUserId:{}  结果：｛｝",userId,friendUserId,e.getMessage());
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void unfollow(String userId, String friendUserId) {
        // 删除关注数据
        SnsFollow follow = new SnsFollow();
        follow.setUserId(userId);
        follow.setFollowedUserId(friendUserId);
        List<SnsFollow> followList = snsFollowMapper.findList(follow);
        if (followList.size()>0) {
            snsFollowMapper.remove(followList.get(0).getId());
        }

        // 删除粉丝数据
        SnsFans fans = new SnsFans();
        fans.setUserId(friendUserId);
        fans.setFollowerUserId(userId);
        List<SnsFans> fansList = snsFansService.findList(fans);
        if (fansList.size()>0) {
            snsFansService.remove(fansList.get(0).getId());
        }

        // 删除双向好友
        SnsFriend snsFriend = new SnsFriend();
        snsFriend.setUserId(userId);
        snsFriend.setFriendUserId(friendUserId);
        List<SnsFriend> list = snsFriendService.findList(snsFriend);
        if (list.size()>0) {
            snsFriendService.remove(list.get(0).getId());
        }
        snsFriend = new SnsFriend();
        snsFriend.setUserId(friendUserId);
        snsFriend.setFriendUserId(userId);
        list = snsFriendService.findList(snsFriend);
        if (list.size()>0) {
            snsFriendService.remove(list.get(0).getId());
        }

        // 接口解除好友关系 双向
        DeleteFriendReq req = new DeleteFriendReq();
        req.setOwnerUserID(userId);
        req.setFriendUserID(friendUserId);
        try{
            openImApiService.deleteFriend(req);
        }catch (BusinessException e)
        {
            log.error("删除好友关系失败：关系信息：｛｝  结果：｛｝",req.toString(),e.getMessage());
        }
        req.setOwnerUserID(friendUserId);
        req.setFriendUserID(userId);
        try{
            openImApiService.deleteFriend(req);
        }catch (BusinessException e)
        {
            log.error("删除好友关系失败：关系信息：｛｝  结果：｛｝",req.toString(),e.getMessage());
        }

    }
}
