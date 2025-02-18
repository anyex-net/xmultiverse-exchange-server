/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.news;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.news.entity.NewsLike;
import com.anyex.apps.news.service.NewsLikeService;

import com.anyex.apps.controller.news.req.ReqNewsLike;
import com.anyex.apps.controller.news.req.ReqNewsLikePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 资讯点赞 控制器
 * <p>File：NewsLikeController.java </p>
 * <p>Title: NewsLikeController </p>
 * <p>Description:NewsLikeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/news/newsLike")
@Api(tags = "资讯点赞")
public class NewsLikeController extends GenericController
{
    @Autowired(required = false)
    private NewsLikeService newsLikeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("news:newsLike:data")
    @ApiOperation(value = "根据ID取资讯点赞", httpMethod = "GET")
    public JsonMessage<NewsLike> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        System.out.println("test");
        return this.getJsonMessage(CommonEnums.SUCCESS, newsLikeService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("news:newsLike:operator")
//    @ApiOperation(value = "保存资讯点赞", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqNewsLike info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            NewsLike entity = new NewsLike();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//            entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                newsLikeService.insert(entity);
//            } else {
//                newsLikeService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }

    @PostMapping(value = "/data")
    @RequiresPermissions("news:newsLike:data")
    @ApiOperation(value = "查询资讯点赞", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqNewsLikePagination pagin) throws BusinessException
    {
        NewsLike entity = new NewsLike();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<NewsLike> result = newsLikeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("news:newsLike:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        newsLikeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
