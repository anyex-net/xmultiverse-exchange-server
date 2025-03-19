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

import com.anyex.apps.user.entity.UserLog;
import com.anyex.apps.user.service.UserLogService;

import com.anyex.apps.controller.user.req.ReqUserLog;
import com.anyex.apps.controller.user.req.ReqUserLogPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户日志 控制器
 * <p>File：UserLogController.java </p>
 * <p>Title: UserLogController </p>
 * <p>Description:UserLogController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userLog")
@Api(tags = "用户日志")
public class UserLogController extends GenericController
{
    @Autowired(required = false)
    private UserLogService userLogService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userLog:data")
    @ApiOperation(value = "根据ID取用户日志", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userLogService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:userLog:operator")
    @ApiOperation(value = "保存用户日志", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserLog info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserLog entity = new UserLog();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                userLogService.insert(entity);
            } else {
                userLogService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userLog:data")
    @ApiOperation(value = "查询用户日志", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserLogPagination pagin) throws BusinessException
    {
        UserLog entity = new UserLog();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserLog> result = userLogService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("user:userLog:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userLogService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
