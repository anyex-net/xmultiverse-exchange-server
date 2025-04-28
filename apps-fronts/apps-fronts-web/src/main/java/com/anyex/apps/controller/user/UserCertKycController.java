/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.user.req.ReqUserCertKyc;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.entity.UserCertKyc;
import com.anyex.apps.user.service.UserCertKycService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getUserCertKyc")
    @ApiOperation(value = "获取用户认证", httpMethod = "GET")
    public JsonMessage<UserCertKyc> getUserCertKyc() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserCertKyc userCertKyc = new UserCertKyc();
        userCertKyc.setUserId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, userCertKycService.selectOne(userCertKyc));
    }

    @PostMapping(value = "/submitUserCerKyc")
    @ApiOperation(value = "提交用户认证", httpMethod = "POST")
    public JsonMessage submitUserCerKyc(@Validated @RequestBody ReqUserCertKyc reqUserCertKyc) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        UserCertKyc userCertKyc = new UserCertKyc();
        BeanUtils.copyProperties(reqUserCertKyc, userCertKyc);
        //
        if (null == reqUserCertKyc.getId())
        {
            userCertKyc.setUserId(principal.getId());
            userCertKyc.setCreateTime(System.currentTimeMillis());
        }
        userCertKyc.setUpdateTime(System.currentTimeMillis());
        userCertKyc.setState(0);
        //
        log.info("userCertKyc:{}", userCertKyc);
        if(null == userCertKyc.getId()){
            userCertKycService.insert(userCertKyc);
        } else {
            userCertKycService.updateByPrimaryKeySelective(userCertKyc);
        }
        //
        return json;
    }
}
