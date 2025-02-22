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
import com.anyex.apps.controller.user.req.ReqUserCertKyc;
import com.anyex.apps.controller.user.req.ReqUserCertKycPagination;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.user.entity.UserCertKyc;
import com.anyex.apps.user.service.UserCertKycService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户认证个人KYC 控制器
 * <p>File：UserCertKycController.java </p>
 * <p>Title: UserCertKycController </p>
 * <p>Description:UserCertKycController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/userCertKyc")
@Api(tags = "用户认证个人KYC")
public class UserCertKycController extends GenericController
{
    @Autowired(required = false)
    private UserCertKycService userCertKycService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("user:userCertKyc:data")
    @ApiOperation(value = "根据ID取用户认证个人KYC", httpMethod = "GET")
    public JsonMessage<UserCertKyc> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userCertKycService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("user:userCertKyc:data")
    @ApiOperation(value = "查询用户认证个人KYC", httpMethod = "POST")
    public JsonMessage<PaginateResult<UserCertKyc>> data(@ModelAttribute ReqUserCertKycPagination pagin) throws BusinessException
    {
        UserCertKyc entity = new UserCertKyc();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserCertKyc> result = userCertKycService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("user:userCertKyc:operator")
    @ApiOperation(value = "保存用户认证个人KYC", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserCertKyc info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserCertKyc entity = new UserCertKyc();
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
                userCertKycService.insert(entity);
            } else {
                userCertKycService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

//    @PostMapping(value = "/del")
//    @RequiresPermissions("user:userCertKyc:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        userCertKycService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }

    @PostMapping(value = "/check")
    @RequiresPermissions("user:userCertKyc:check")
    @ApiOperation(value = "审核", httpMethod = "POST")
    public JsonMessage check(Long id, Integer state) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserCertKyc userCertKyc = userCertKycService.selectByPrimaryKey(id);
        if (null == userCertKyc) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        userCertKyc.setState(state);
        userCertKyc.setCheckBy(principal.getUserName());
        userCertKyc.setCheckTime(System.currentTimeMillis());
        userCertKycService.updateByPrimaryKeySelective(userCertKyc);
        return json;
    }

}
