package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.system.entity.SysFrontRoleRes;
import com.anyex.apps.system.service.SysFrontRoleResService;
import com.anyex.apps.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 前端角色权限信息表 控制器
 * <p>File：FrontRoleResController.java </p>
 * <p>Title: FrontRoleResController </p>
 * <p>Description:FrontRoleResController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
//@RestController
//@RequestMapping("/system/frontRoleRes")
//@Api(tags = "前端角色权限信息")
public class SysFrontRoleResController extends GenericController
{
    @Autowired(required = false)
    private SysFrontRoleResService frontRoleResService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "根据ID取前端角色权限信息表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, frontRoleResService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "保存前端角色权限信息表", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute SysFrontRoleRes info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            frontRoleResService.save(info);
        }
        return json;
    }

    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "保存角色授权信息", httpMethod = "POST")
    @RequestMapping(value = "/saveGrant", method = RequestMethod.POST)
    public JsonMessage saveGrant(Long roleId, String moduleIds) throws BusinessException
    {
        if (null == roleId || StringUtils.isBlank(moduleIds))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        frontRoleResService.saveGrant(roleId, moduleIds);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "查询前端角色权限信息表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute SysFrontRoleRes entity, @Validated @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<SysFrontRoleRes> result = frontRoleResService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        frontRoleResService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
