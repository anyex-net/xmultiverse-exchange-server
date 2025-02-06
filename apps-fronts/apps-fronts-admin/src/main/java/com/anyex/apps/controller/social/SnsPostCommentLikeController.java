/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.social.entity.SnsPostCommentLike;
import com.anyex.apps.social.service.SnsPostCommentLikeService;

import com.anyex.apps.controller.social.req.ReqSnsPostCommentLike;
import com.anyex.apps.controller.social.req.ReqSnsPostCommentLikePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交帖子评论点赞 控制器
 * <p>File：SnsPostCommentLikeController.java </p>
 * <p>Title: SnsPostCommentLikeController </p>
 * <p>Description:SnsPostCommentLikeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsPostCommentLike")
@Api(tags = "社交帖子评论点赞")
public class SnsPostCommentLikeController extends GenericController
{
    @Autowired(required = false)
    private SnsPostCommentLikeService snsPostCommentLikeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsPostCommentLike:data")
    @ApiOperation(value = "根据ID取社交帖子评论点赞", httpMethod = "GET")
    public JsonMessage<SnsPostCommentLike> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsPostCommentLikeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsPostCommentLike:operator")
    @ApiOperation(value = "保存社交帖子评论点赞", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsPostCommentLike info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsPostCommentLike entity = new SnsPostCommentLike();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                snsPostCommentLikeService.insert(entity);
            } else {
                snsPostCommentLikeService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsPostCommentLike:data")
    @ApiOperation(value = "查询社交帖子评论点赞", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsPostCommentLike>> data(@ModelAttribute ReqSnsPostCommentLikePagination pagin) throws BusinessException
    {
        SnsPostCommentLike entity = new SnsPostCommentLike();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsPostCommentLike> result = snsPostCommentLikeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsPostCommentLike:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsPostCommentLikeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
