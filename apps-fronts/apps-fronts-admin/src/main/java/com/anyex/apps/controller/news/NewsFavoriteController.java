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

import com.anyex.apps.news.entity.NewsFavorite;
import com.anyex.apps.news.service.NewsFavoriteService;

import com.anyex.apps.controller.news.req.ReqNewsFavorite;
import com.anyex.apps.controller.news.req.ReqNewsFavoritePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 资讯收藏 控制器
 * <p>File：NewsFavoriteController.java </p>
 * <p>Title: NewsFavoriteController </p>
 * <p>Description:NewsFavoriteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/news/newsFavorite")
@Api(tags = "资讯收藏")
public class NewsFavoriteController extends GenericController
{
    @Autowired(required = false)
    private NewsFavoriteService newsFavoriteService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("news:newsFavorite:data")
    @ApiOperation(value = "根据ID取资讯收藏", httpMethod = "GET")
    public JsonMessage<NewsFavorite> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, newsFavoriteService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("news:newsFavorite:operator")
//    @ApiOperation(value = "保存资讯收藏", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqNewsFavorite info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            NewsFavorite entity = new NewsFavorite();
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
//                newsFavoriteService.insert(entity);
//            } else {
//                newsFavoriteService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }

    @PostMapping(value = "/data")
    @RequiresPermissions("news:newsFavorite:data")
    @ApiOperation(value = "查询资讯收藏", httpMethod = "POST")
    public JsonMessage<PaginateResult<NewsFavorite>> data(@ModelAttribute ReqNewsFavoritePagination pagin) throws BusinessException
    {
        NewsFavorite entity = new NewsFavorite();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<NewsFavorite> result = newsFavoriteService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("news:newsFavorite:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        newsFavoriteService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
