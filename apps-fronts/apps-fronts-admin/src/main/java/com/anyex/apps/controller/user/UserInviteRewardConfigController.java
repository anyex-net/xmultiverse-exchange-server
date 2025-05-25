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
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.UserHoldAmountRewardConfig;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.user.entity.UserInviteRewardConfig;
import com.anyex.apps.user.service.UserInviteRewardConfigService;

import com.anyex.apps.controller.user.req.ReqUserInviteRewardConfig;
import com.anyex.apps.controller.user.req.ReqUserInviteRewardConfigPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户邀请返佣奖励配置 控制器
 * <p>File：UserInviteRewardConfigController.java </p>
 * <p>Title: UserInviteRewardConfigController </p>
 * <p>Description:UserInviteRewardConfigController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userInviteRewardConfig")
@Api(tags = "用户邀请返佣奖励配置")
public class UserInviteRewardConfigController extends GenericController
{
    @Autowired(required = false)
    private UserInviteRewardConfigService userInviteRewardConfigService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userInviteRewardConfig:data")
    @ApiOperation(value = "根据ID取用户邀请返佣奖励配置", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userInviteRewardConfigService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:userInviteRewardConfig:operator")
    @ApiOperation(value = "保存用户邀请返佣奖励配置", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserInviteRewardConfig info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserInviteRewardConfig entity = new UserInviteRewardConfig();
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
                userInviteRewardConfigService.insert(entity);
            } else {
                userInviteRewardConfigService.updateByPrimaryKeySelective(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userInviteRewardConfig:data")
    @ApiOperation(value = "查询用户邀请返佣奖励配置", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserInviteRewardConfigPagination pagin) throws BusinessException
    {
        UserInviteRewardConfig entity = new UserInviteRewardConfig();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserInviteRewardConfig> result = userInviteRewardConfigService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("user:userInviteRewardConfig:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userInviteRewardConfigService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/check")
    @RequiresPermissions("user:userInviteRewardConfig:operator")
    @ApiOperation(value = "审核", httpMethod = "POST")
    public JsonMessage check(Long id, Integer state) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserInviteRewardConfig config = userInviteRewardConfigService.selectByPrimaryKey(id);
        if (null == config) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        config.setState(state);
        config.setUpdateBy(principal.getUserName());
        config.setUpdateTime(System.currentTimeMillis());
        userInviteRewardConfigService.updateByPrimaryKeySelective(config);
        return json;
    }
}
