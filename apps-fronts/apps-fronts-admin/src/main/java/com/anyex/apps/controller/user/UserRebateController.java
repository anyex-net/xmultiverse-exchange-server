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

import com.anyex.apps.user.entity.UserRebate;
import com.anyex.apps.user.service.UserRebateService;

import com.anyex.apps.controller.user.req.ReqUserRebate;
import com.anyex.apps.controller.user.req.ReqUserRebatePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户返佣记录 控制器
 * <p>File：UserRebateController.java </p>
 * <p>Title: UserRebateController </p>
 * <p>Description:UserRebateController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userRebate")
@Api(description = "用户返佣记录")
public class UserRebateController extends GenericController
{
    @Autowired(required = false)
    private UserRebateService userRebateService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userRebate:data")
    @ApiOperation(value = "根据ID取用户返佣记录", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userRebateService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:userRebate:operator")
    @ApiOperation(value = "保存用户返佣记录", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserRebate info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserRebate entity = new UserRebate();
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
                userRebateService.insert(entity);
            } else {
                userRebateService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userRebate:data")
    @ApiOperation(value = "查询用户返佣记录", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserRebatePagination pagin) throws BusinessException
    {
        UserRebate entity = new UserRebate();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserRebate> result = userRebateService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("user:userRebate:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userRebateService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
