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
import com.anyex.apps.system.entity.SysRoleData;
import com.anyex.apps.system.service.SysRoleDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RoleData 控制器
 * <p>File：RoleDataController.java </p>
 * <p>Title: RoleDataController </p>
 * <p>Description:RoleDataController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
//@RestController
//@RequestMapping("/system/roleData")
//@Api(tags ="角色数据")
public class SysRoleDataController extends GenericController
{
    @Autowired(required = false)
    private SysRoleDataService roleDataService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("system:roleData:data")
    @ApiOperation(value = "根据ID取RoleData", httpMethod = "GET")
    public JsonMessage<SysRoleData> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, roleDataService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("system:roleData:operator")
    @ApiOperation(value = "保存RoleData", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute SysRoleData info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            roleDataService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("system:roleData:data")
    @ApiOperation(value = "查询RoleData", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysRoleData>> data(@ModelAttribute SysRoleData entity, @Validated @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<SysRoleData> result = roleDataService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("system:roleData:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        roleDataService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
