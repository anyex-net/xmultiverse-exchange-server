/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysAppDevice;
import com.anyex.apps.common.service.SysAppDeviceService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysAppDevicePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * AppDevice 控制器
 * <p>File：AppDeviceController.java </p>
 * <p>Title: AppDeviceController </p>
 * <p>Description:AppDeviceController </p>
 * <p>Copyright: Copyright (c) May 26, 2021 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "app设备信息")
public class SysAppDeviceController extends GenericController
{
    @Autowired(required = false)
    private SysAppDeviceService appDeviceService;

    @GetMapping(value = "/appDevice/findBy")
    @RequiresPermissions("common:appDevice:data")
    @ApiOperation(value = "根据ID取AppDevice", httpMethod = "GET")
    public JsonMessage findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, appDeviceService.selectByPrimaryKey(id));
    }

//    @PostMapping(value = "/appDevice/save")
//    @RequiresPermissions("common:appDevice:operator")
//    @ApiOperation(value = "保存AppDevice", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute SysAppDevice info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            appDeviceService.save(info);
//        }
//        return json;
//    }

    @PostMapping(value = "/appDevice/data")
    @RequiresPermissions("common:appDevice:data")
    @ApiOperation(value = "查询AppDevice", httpMethod = "POST")
    public JsonMessage data(@Validated @ModelAttribute ReqSysAppDevicePagination reqSysAppDevicePagination) throws BusinessException
    {
        //
        SysAppDevice sysAppDevice = new SysAppDevice();
        BeanUtils.copyProperties(reqSysAppDevicePagination, sysAppDevice);
        //
        PaginateResult<SysAppDevice> result = appDeviceService.search(reqSysAppDevicePagination, sysAppDevice);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/appDevice/del")
    @RequiresPermissions("common:appDevice:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        appDeviceService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
