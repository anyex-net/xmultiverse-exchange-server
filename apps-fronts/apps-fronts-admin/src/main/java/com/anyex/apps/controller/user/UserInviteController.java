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

import com.anyex.apps.user.entity.UserInvite;
import com.anyex.apps.user.service.UserInviteService;

import com.anyex.apps.controller.user.req.ReqUserInvite;
import com.anyex.apps.controller.user.req.ReqUserInvitePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户邀请关系 控制器
 * <p>File：UserInviteController.java </p>
 * <p>Title: UserInviteController </p>
 * <p>Description:UserInviteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userInvite")
@Api(description = "用户邀请关系")
public class UserInviteController extends GenericController
{
    @Autowired(required = false)
    private UserInviteService userInviteService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userInvite:data")
    @ApiOperation(value = "根据ID取用户邀请关系", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userInviteService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("user:userInvite:operator")
//    @ApiOperation(value = "保存用户邀请关系", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqUserInvite info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            UserInvite entity = new UserInvite();
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
//                userInviteService.insert(entity);
//            } else {
//                userInviteService.updateByPrimaryKeySelective(entity);
//            }
//        }
//        return json;
//    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userInvite:data")
    @ApiOperation(value = "查询用户邀请关系", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserInvitePagination pagin) throws BusinessException
    {
        UserInvite entity = new UserInvite();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserInvite> result = userInviteService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("user:userInvite:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        userInviteService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
