/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.operation.req.ReqAppActivationInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.operation.entity.AppActivationInfo;
import com.anyex.apps.operation.service.AppActivationInfoService;
import com.anyex.apps.utils.NetworkUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * APP激活信息 控制器
 * <p>File：AppActivationInfoController.java </p>
 * <p>Title: AppActivationInfoController </p>
 * <p>Description:AppActivationInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/operation/appActivationInfo")
@Api(tags = "APP激活信息")
public class AppActivationInfoController extends GenericController
{
    @Autowired(required = false)
    private AppActivationInfoService appActivationInfoService;

    @PostMapping(value = "/activation")
    @ApiOperation(value = "APP激活", httpMethod = "POST")
    public JsonMessage activation(HttpServletRequest request, @RequestBody ReqAppActivationInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AppActivationInfo appActivationInfo = new AppActivationInfo();
            BeanUtils.copyProperties(info, appActivationInfo);
            //
            appActivationInfo.setIp(NetworkUtils.getIpAddr(request));
            appActivationInfo.setCreateTime(System.currentTimeMillis());
            appActivationInfo.setUpdateTime(System.currentTimeMillis());
            //
            log.info("appActivationInfo:{}", appActivationInfo);
            appActivationInfoService.insert(appActivationInfo);
        }
        return json;
    }
}
