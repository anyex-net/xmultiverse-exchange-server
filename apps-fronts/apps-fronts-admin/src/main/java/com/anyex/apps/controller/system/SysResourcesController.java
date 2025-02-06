/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.system.req.ReqSysResources;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.CalendarUtils;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.service.SysResourcesService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源菜单信息表 控制器
 * <p>File：ResourcesController.java </p>
 * <p>Title: ResourcesController </p>
 * <p>Description:ResourcesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(tags = "资源管理")
public class SysResourcesController extends GenericController
{
    @Autowired(required = false)
    private SysResourcesService resourcesService;
    
    @GetMapping(value = "/resource/findBy")
    @RequiresPermissions("system:resource:data")
    @ApiOperation(value = "根据ID取资源信息", httpMethod = "GET")
    public JsonMessage<SysResources> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, resourcesService.selectByPrimaryKey(id));
    }
    
    @RequiresPermissions("system:resource:operator")
    @ApiOperation(value = "保存或更新资源信息", httpMethod = "POST")
    @RequestMapping(value = "/resource/save", method = RequestMethod.POST)
    public JsonMessage save(@ModelAttribute ReqSysResources info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        SysResources sysResources = new SysResources();
        BeanUtils.copyProperties(info, sysResources);
        //
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            sysResources.setCreateBy(principal.getId());
            sysResources.setCreateDate(currentDate);
        }
        sysResources.setUpdateBy(principal.getId());
        sysResources.setUpdateDate(currentDate);
        log.info("sysResources:{}", sysResources);
        resourcesService.save(sysResources);
        return json;
    }
    
    @GetMapping(value = "/resource/tree")
    @RequiresPermissions("system:resource:data")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public JsonMessage<List<SysResources>> tree() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, resourcesService.treeData());
    }
    
    @RequiresPermissions("system:resource:data")
    @ApiOperation(value = "查询资源信息", httpMethod = "POST")
    @RequestMapping(value = "/resource/data", method = RequestMethod.POST)
    public JsonMessage<List<SysResources>> data(Long id) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, resourcesService.findList(new SysResources(id)));
    }
    
    @RequiresPermissions("system:resource:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @RequestMapping(value = "/resource/del", method = RequestMethod.POST)
    public JsonMessage del(@RequestParam("id") Long id) throws BusinessException
    {
        SysResources resources = new SysResources();
        resources.setParentId(id);
        List<SysResources> resourcesList = resourcesService.findList(resources);
        if (resourcesList == null || resourcesList.size() == 0) {
            resourcesService.remove(id);
            return this.getJsonMessage(CommonEnums.SUCCESS);
        } else {
            log.error("存在子节点，当前节点无法删除!");
            return this.getJsonMessage(CommonEnums.ERROR_EXIST_SUBNODE);
        }
    }
}
