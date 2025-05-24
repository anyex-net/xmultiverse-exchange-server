/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.base.entity.UserInstrumentsFavorite;
import com.anyex.apps.base.service.UserInstrumentsFavoriteService;

import com.anyex.apps.controller.base.req.ReqUserInstrumentsFavorite;
import com.anyex.apps.controller.base.req.ReqUserInstrumentsFavoritePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户交易产品收藏 控制器
 * <p>File：UserInstrumentsFavoriteController.java </p>
 * <p>Title: UserInstrumentsFavoriteController </p>
 * <p>Description:UserInstrumentsFavoriteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/userInstrumentsFavorite")
@Api(tags = "用户交易产品收藏")
public class UserInstrumentsFavoriteController extends GenericController
{
    @Autowired(required = false)
    private UserInstrumentsFavoriteService userInstrumentsFavoriteService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("base:userInstrumentsFavorite:data")
    @ApiOperation(value = "根据ID取用户交易产品收藏", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userInstrumentsFavoriteService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("base:userInstrumentsFavorite:operator")
    @ApiOperation(value = "保存用户交易产品收藏", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserInstrumentsFavorite info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserInstrumentsFavorite entity = new UserInstrumentsFavorite();
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
                userInstrumentsFavoriteService.insert(entity);
            } else {
                userInstrumentsFavoriteService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("base:userInstrumentsFavorite:data")
    @ApiOperation(value = "查询用户交易产品收藏", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserInstrumentsFavoritePagination pagin) throws BusinessException
    {
        UserInstrumentsFavorite entity = new UserInstrumentsFavorite();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserInstrumentsFavorite> result = userInstrumentsFavoriteService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("base:userInstrumentsFavorite:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userInstrumentsFavoriteService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
