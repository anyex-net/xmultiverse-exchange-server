/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.model.SnsPostCommentModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsPostComment;
import com.anyex.apps.social.mapper.SnsPostCommentMapper;

import java.util.List;

/**
 * 社交帖子评论 服务实现类
 * <p>File：SnsPostCommentServiceImpl.java </p>
 * <p>Title: SnsPostCommentServiceImpl </p>
 * <p>Description:SnsPostCommentServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsPostCommentServiceImpl extends GenericServiceImpl<SnsPostComment> implements SnsPostCommentService
{
    protected SnsPostCommentMapper snsPostCommentMapper;
    @Autowired
    private SnsPostService postService;

    @Autowired(required = false)
    public SnsPostCommentServiceImpl(SnsPostCommentMapper snsPostCommentMapper)
    {
        super(snsPostCommentMapper);
        this.snsPostCommentMapper = snsPostCommentMapper;
    }

    @Override
    public PaginateResult<SnsPostCommentModel> getComments(Pagination pagin, Long postId, Long commentId, String viewerUserId) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostCommentModel> pageInfo = PageInfo.of(snsPostCommentMapper.getComments(postId, commentId,viewerUserId));
        SnsPost post = postService.selectByPrimaryKey(postId);
        if(post.getOpenness() == 0)
        {
            for(SnsPostCommentModel model:pageInfo.getList())
            {
                model.setCommentAccountName("anonymous");
                model.setCommentHeadUrl("anonymous");
                model.setTopUserAccountName("anonymous");
                model.setTopUserHeadUrl("anonymous");
            }
        }
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<SnsPostCommentModel> getAccountComments(Pagination pagin, String userId,Integer isRead) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostCommentModel> pageInfo = PageInfo.of(snsPostCommentMapper.getAccountComments(userId, isRead));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public List<SnsPostCommentModel> getAccountCommentsUnRead(String userId) throws BusinessException {
        return snsPostCommentMapper.getAccountComments(userId, 0);
    }
}
