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

import com.anyex.apps.user.entity.UserHoldAmountRewardConfig;
import com.anyex.apps.user.service.UserHoldAmountRewardConfigService;

import com.anyex.apps.controller.user.req.ReqUserHoldAmountRewardConfig;
import com.anyex.apps.controller.user.req.ReqUserHoldAmountRewardConfigPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户持有数量奖励配置 控制器
 * <p>File：UserHoldAmountRewardConfigController.java </p>
 * <p>Title: UserHoldAmountRewardConfigController </p>
 * <p>Description:UserHoldAmountRewardConfigController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userHoldAmountRewardConfig")
@Api(tags = "用户持有数量奖励配置")
public class UserHoldAmountRewardConfigController extends GenericController
{
    @Autowired(required = false)
    private UserHoldAmountRewardConfigService userHoldAmountRewardConfigService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userHoldAmountRewardConfig:data")
    @ApiOperation(value = "根据ID取用户持有数量奖励配置", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userHoldAmountRewardConfigService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:userHoldAmountRewardConfig:operator")
    @ApiOperation(value = "保存用户持有数量奖励配置", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserHoldAmountRewardConfig info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserHoldAmountRewardConfig entity = new UserHoldAmountRewardConfig();
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
                userHoldAmountRewardConfigService.insert(entity);
            } else {
                userHoldAmountRewardConfigService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userHoldAmountRewardConfig:data")
    @ApiOperation(value = "查询用户持有数量奖励配置", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserHoldAmountRewardConfigPagination pagin) throws BusinessException
    {
        UserHoldAmountRewardConfig entity = new UserHoldAmountRewardConfig();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserHoldAmountRewardConfig> result = userHoldAmountRewardConfigService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("user:userHoldAmountRewardConfig:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userHoldAmountRewardConfigService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
