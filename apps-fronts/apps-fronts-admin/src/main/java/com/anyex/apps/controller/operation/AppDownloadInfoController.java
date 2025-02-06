/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.operation.entity.AppDownloadInfo;
import com.anyex.apps.operation.service.AppDownloadInfoService;

import com.anyex.apps.controller.operation.req.ReqAppDownloadInfoPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

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

    @GetMapping(value = "/findBy")
    @RequiresPermissions("operation:appDownloadInfo:data")
    @ApiOperation(value = "根据ID取APP下载信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, appDownloadInfoService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("operation:appDownloadInfo:operator")
//    @ApiOperation(value = "保存APP下载信息", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqAppDownloadInfo info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            AppDownloadInfo entity = new AppDownloadInfo();
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
//                appDownloadInfoService.insert(entity);
//            } else {
//                appDownloadInfoService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }

    @PostMapping(value = "/data")
    @RequiresPermissions("operation:appDownloadInfo:data")
    @ApiOperation(value = "查询APP下载信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqAppDownloadInfoPagination pagin) throws BusinessException
    {
        AppDownloadInfo entity = new AppDownloadInfo();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AppDownloadInfo> result = appDownloadInfoService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("operation:appDownloadInfo:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        appDownloadInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
