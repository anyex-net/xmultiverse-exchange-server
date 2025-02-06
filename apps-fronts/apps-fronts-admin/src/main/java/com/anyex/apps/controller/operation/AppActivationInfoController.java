/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation;

import com.anyex.apps.controller.operation.req.ReqAppActivationInfoPagination;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.operation.entity.AppActivationInfo;
import com.anyex.apps.operation.service.AppActivationInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

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

    @GetMapping(value = "/findBy")
    @RequiresPermissions("operation:appActivationInfo:data")
    @ApiOperation(value = "根据ID取APP激活信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, appActivationInfoService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("operation:appActivationInfo:operator")
//    @ApiOperation(value = "保存APP激活信息", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqAppActivationInfo info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            AppActivationInfo entity = new AppActivationInfo();
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
//                appActivationInfoService.insert(entity);
//            } else {
//                appActivationInfoService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }

    @PostMapping(value = "/data")
    @RequiresPermissions("operation:appActivationInfo:data")
    @ApiOperation(value = "查询APP激活信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqAppActivationInfoPagination pagin) throws BusinessException
    {
        AppActivationInfo entity = new AppActivationInfo();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<AppActivationInfo> result = appActivationInfoService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("operation:appActivationInfo:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        appActivationInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
