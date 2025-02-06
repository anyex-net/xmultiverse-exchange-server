/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.system.req.ReqSysAccessLogPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.system.entity.SysAccessLog;
import com.anyex.apps.system.service.SysAccessLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * AccessLog 控制器
 * <p>File：AccessLogController.java </p>
 * <p>Title: AccessLogController </p>
 * <p>Description:AccessLogController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/system/accessLog")
@Api(tags = "访问日志")
public class SysAccessLogController extends GenericController
{
    @Autowired(required = false)
    private SysAccessLogService accessLogService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("system:accessLog:data")
    @ApiOperation(value = "根据ID取AccessLog", httpMethod = "GET")
    public JsonMessage<SysAccessLog> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, accessLogService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("system:accessLog:data")
    @ApiOperation(value = "查询AccessLog", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysAccessLog>> data(@Validated @ModelAttribute ReqSysAccessLogPagination reqSysAccessLogPagination) throws BusinessException
    {
        //
        SysAccessLog sysAccessLog = new SysAccessLog();
        BeanUtils.copyProperties(reqSysAccessLogPagination, sysAccessLog);
        //
        PaginateResult<SysAccessLog> result = accessLogService.search(reqSysAccessLogPagination, sysAccessLog);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("system:accessLog:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("iidsd") String ids) throws BusinessException
    {
        accessLogService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
