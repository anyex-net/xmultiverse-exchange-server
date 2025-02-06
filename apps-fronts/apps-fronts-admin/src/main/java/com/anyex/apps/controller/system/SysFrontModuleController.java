package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.system.entity.SysFrontModule;
import com.anyex.apps.system.service.SysFrontModuleService;
import com.anyex.apps.utils.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 前端功能模块表 控制器
 * <p>File：FrontModuleController.java </p>
 * <p>Title: FrontModuleController </p>
 * <p>Description:FrontModuleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
//@RestController
//@RequestMapping("/system/frontModule")
//@Api(tags = "前端功能模块")
public class SysFrontModuleController extends GenericController
{
    @Autowired(required = false)
    private SysFrontModuleService frontModuleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("system:frontModule:data")
    @ApiOperation(value = "根据ID取前端功能模块表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, frontModuleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/findByRoleId")
    @RequiresPermissions("system:frontModule:data")
    @ApiOperation(value = "根据角色id查询前端功能模块表", httpMethod = "POST")
    public JsonMessage data(Long roleId) throws BusinessException
    {
        List<SysFrontModule> result = frontModuleService.findByRoleId(roleId);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("system:frontModule:operator")
    @ApiOperation(value = "保存前端功能模块表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute SysFrontModule info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        info.setCreateBy(principal.getId());
        info.setCreateDate(System.currentTimeMillis());
        if (beanValidator(json, info))
        {
            frontModuleService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("system:frontModule:data")
    @ApiOperation(value = "查询前端功能模块表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute SysFrontModule entity, @Validated @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<SysFrontModule> result = frontModuleService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/allData")
    @RequiresPermissions("system:frontModule:data")
    @ApiOperation(value = "查询前端功能模块表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute SysFrontModule entity) throws BusinessException
    {
        List<SysFrontModule> list = frontModuleService.findList(entity);
        return getJsonMessage(CommonEnums.SUCCESS, list);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("system:frontModule:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        frontModuleService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
