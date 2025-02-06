/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.controller.common.req.ReqSysParameter;
import com.anyex.apps.controller.common.req.ReqSysParameterPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.common.entity.SysParameter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 参数配置 控制器
 * <p>File：ParameterController.java </p>
 * <p>Title: ParameterController </p>
 * <p>Description:ParameterController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/common/parameter")
@Api(tags = "参数配置")
public class SysParameterController extends GenericController
{
    @Autowired(required = false)
    private SysParameterService parameterService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("common:parameter:data")
    @ApiOperation(value = "根据ID取参数配置", httpMethod = "GET")
    public JsonMessage<SysParameter> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, parameterService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("common:parameter:operator")
    @ApiOperation(value = "保存或更新参数配置", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSysParameter info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SysParameter sysParameter = new SysParameter();
            BeanUtils.copyProperties(info, sysParameter);
            //
            log.info("sysParameter:{}", sysParameter);
            if(null == sysParameter.getId()){
                parameterService.insert(sysParameter);
            } else {
                parameterService.updateByPrimaryKey(sysParameter);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("common:parameter:data")
    @ApiOperation(value = "查询参数配置", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysParameter>> data(@Validated @ModelAttribute ReqSysParameterPagination reqSysParameterPagination) throws BusinessException
    {
        //
        SysParameter sysParameter = new SysParameter();
        BeanUtils.copyProperties(reqSysParameterPagination, sysParameter);
        //
        PaginateResult<SysParameter> result = parameterService.search(reqSysParameterPagination, sysParameter);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("common:parameter:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        parameterService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
