/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.operation.req.ReqAppDownloadInfo;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.operation.entity.AppDownloadInfo;
import com.anyex.apps.operation.service.AppDownloadInfoService;
import com.anyex.apps.utils.NetworkUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * APP下载信息 控制器
 * <p>File：AppDownloadInfoController.java </p>
 * <p>Title: AppDownloadInfoController </p>
 * <p>Description:AppDownloadInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/operation/appDownloadInfo")
@Api(tags = "APP下载信息")
public class AppDownloadInfoController extends GenericController
{
    @Autowired(required = false)
    private AppDownloadInfoService appDownloadInfoService;

    @PostMapping(value = "/downloadClick")
    @ApiOperation(value = "APP下载点击", httpMethod = "POST")
    public JsonMessage downloadClick(HttpServletRequest request, @RequestBody ReqAppDownloadInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            AppDownloadInfo appDownloadInfo = new AppDownloadInfo();
            BeanUtils.copyProperties(info, appDownloadInfo);
            //
            appDownloadInfo.setIp(NetworkUtils.getIpAddr(request));
            appDownloadInfo.setCreateTime(System.currentTimeMillis());
            appDownloadInfo.setUpdateTime(System.currentTimeMillis());
            //
            log.info("appDownloadInfo:{}", appDownloadInfo);
            appDownloadInfoService.insert(appDownloadInfo);
        }
        return json;
    }
}
