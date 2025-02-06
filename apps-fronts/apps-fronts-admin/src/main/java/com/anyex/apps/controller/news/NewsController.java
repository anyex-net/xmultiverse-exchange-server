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
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.news.entity.News;
import com.anyex.apps.news.service.NewsService;

import com.anyex.apps.controller.news.req.ReqNews;
import com.anyex.apps.controller.news.req.ReqNewsPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 资讯 控制器
 * <p>File：NewsController.java </p>
 * <p>Title: NewsController </p>
 * <p>Description:NewsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/news/news")
@Api(tags = "资讯")
public class NewsController extends GenericController
{
    @Autowired(required = false)
    private NewsService newsService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("news:news:data")
    @ApiOperation(value = "根据ID取资讯", httpMethod = "GET")
    public JsonMessage<News> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, newsService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("news:news:operator")
    @ApiOperation(value = "保存资讯", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqNews info) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            //
            if (null == info.getId())
            {
                News entity = new News();
                BeanUtils.copyProperties(info, entity);
                //
                entity.setViewNum(0);
                entity.setFavoriteNum(0);
                entity.setLikeNum(0);
                entity.setCommentNum(0);
                entity.setShareNum(0);
                entity.setState(0); // 0待发布
                entity.setCreateTime(System.currentTimeMillis());
                entity.setCreateName(principal.getUserName());
                //
                log.info("新增News:{}", entity);
                newsService.insert(entity);
            } else {
                News entity = newsService.selectByPrimaryKey(info.getId());
                BeanUtils.copyProperties(info, entity);
                //
                entity.setState(0); // 0待发布
                entity.setUpdateTime(System.currentTimeMillis());
                //
                log.info("修改News:{}", entity);
                newsService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/publish")
    @RequiresPermissions("news:news:operator")
    @ApiOperation(value = "发布资讯", httpMethod = "POST")
    public JsonMessage publish(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        News news = newsService.selectByPrimaryKey(id);
        news.setState(1); // 1已发布
        news.setPublishTime(System.currentTimeMillis());
        news.setPublishName(principal.getUserName());
        newsService.updateByPrimaryKeySelective(news);
        //
        return json;
    }

    @PostMapping(value = "/unpublish")
    @RequiresPermissions("news:news:operator")
    @ApiOperation(value = "取消发布资讯", httpMethod = "POST")
    public JsonMessage unpublish(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        News news = newsService.selectByPrimaryKey(id);
        news.setState(0); // 0待发布
        news.setPublishTime(System.currentTimeMillis());
        news.setPublishName(principal.getUserName());
        newsService.updateByPrimaryKeySelective(news);
        //
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("news:news:data")
    @ApiOperation(value = "查询资讯", httpMethod = "POST")
    public JsonMessage<PaginateResult<News>> data(@ModelAttribute ReqNewsPagination pagin) throws BusinessException
    {
        News entity = new News();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<News> result = newsService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("news:news:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        newsService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
