/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.user.entity.UserApi;
import com.anyex.apps.user.service.UserApiService;

import com.anyex.apps.controller.user.req.ReqUserApi;
import com.anyex.apps.controller.user.req.ReqUserApiPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户API 控制器
 * <p>File：UserApiController.java </p>
 * <p>Title: UserApiController </p>
 * <p>Description:UserApiController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userApi")
@Api(tags = "用户API")
public class UserApiController extends GenericController
{
    @Autowired(required = false)
    private UserApiService userApiService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userApi:data")
    @ApiOperation(value = "根据ID取用户API", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userApiService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:userApi:operator")
    @ApiOperation(value = "保存用户API", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserApi info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserApi entity = new UserApi();
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
                userApiService.insert(entity);
            } else {
                userApiService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userApi:data")
    @ApiOperation(value = "查询用户API", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserApiPagination pagin) throws BusinessException
    {
        UserApi entity = new UserApi();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserApi> result = userApiService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("user:userApi:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userApiService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
