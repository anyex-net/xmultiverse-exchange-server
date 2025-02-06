/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.system.entity.SysUserData;
import com.anyex.apps.system.service.SysUserDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * UserData 控制器
 * <p>File：UserDataController.java </p>
 * <p>Title: UserDataController </p>
 * <p>Description:UserDataController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
//@RestController
//@RequestMapping("/system/userData")
//@Api(tags = "用户数据")
public class SysUserDataController extends GenericController
{
    @Autowired(required = false)
    private SysUserDataService userDataService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("system:userData:data")
    @ApiOperation(value = "根据ID取UserData", httpMethod = "GET")
    public JsonMessage<SysUserData> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userDataService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("system:userData:operator")
    @ApiOperation(value = "保存UserData", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute SysUserData info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            userDataService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("system:userData:data")
    @ApiOperation(value = "查询UserData", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysUserData>> data(@ModelAttribute SysUserData entity, @Validated @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<SysUserData> result = userDataService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("system:userData:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userDataService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
