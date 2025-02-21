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
import com.anyex.apps.controller.user.req.ReqUser;
import com.anyex.apps.controller.user.req.ReqUserPagination;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户信息 控制器
 * <p>File：UserController.java </p>
 * <p>Title: UserController </p>
 * <p>Description:UserController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/user")
@Api(tags = "用户信息")
public class UserController extends GenericController
{
    @Autowired(required = false)
    private UserService userService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:user:data")
    @ApiOperation(value = "根据ID取用户信息", httpMethod = "GET")
    public JsonMessage<User> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:user:data")
    @ApiOperation(value = "查询用户信息", httpMethod = "POST")
    public JsonMessage<PaginateResult<User>> data(@ModelAttribute ReqUserPagination pagin) throws BusinessException
    {
        User entity = new User();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<User> result = userService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:user:operator")
    @ApiOperation(value = "保存用户信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUser info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            User entity = new User();
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
                userService.insert(entity);
            } else {
                userService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

//    @PostMapping(value = "/del")
//    @RequiresPermissions("user:user:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        userService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }

    @PostMapping(value = "/frozenOrUnfrozen")
    @RequiresPermissions("user:user:operator")
    @ApiOperation(value = "冻结或解冻", httpMethod = "POST")
    public JsonMessage frozenOrUnfrozen(Long id, Integer state) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        User user = userService.selectByPrimaryKey(id);
        if (null == user) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        //
        if (state.intValue() == 0) {
            user.setState(1); //冻结
        } else if (state.intValue() == 1){
            user.setState(0); //解冻
            user.setThawTime(System.currentTimeMillis());
        }
        user.setUpdateBy(principal.getUserName());
        user.setUpdateTime(System.currentTimeMillis());
        userService.updateByPrimaryKeySelective(user);
        return json;
    }

}
