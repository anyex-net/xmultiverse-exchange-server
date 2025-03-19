/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.spot.entity.Operlog;
import com.anyex.apps.spot.service.OperlogService;

import com.anyex.apps.controller.spot.req.ReqOperlog;
import com.anyex.apps.controller.spot.req.ReqOperlogPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * operlog_example 控制器
 * <p>File：OperlogExampleController.java </p>
 * <p>Title: OperlogExampleController </p>
 * <p>Description:OperlogExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/operlogExample")
@Api(description = "operlog_example")
public class OperlogController extends GenericController
{
    @Autowired(required = false)
    private OperlogService operlogExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:operlogExample:data")
    @ApiOperation(value = "根据ID取operlog_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, operlogExampleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:operlogExample:data")
    @ApiOperation(value = "查询operlog_example", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqOperlogPagination pagin) throws BusinessException
    {
        Operlog entity = new Operlog();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<Operlog> result = operlogExampleService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("spot:operlogExample:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        operlogExampleService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
