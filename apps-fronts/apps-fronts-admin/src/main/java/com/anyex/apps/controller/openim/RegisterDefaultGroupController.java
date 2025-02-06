/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.openim.api.group.req.GetGroupsInfoReq;
import com.anyex.openim.api.group.resp.GetGroupsInfoResp;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.openim.entity.RegisterDefaultGroup;
import com.anyex.apps.openim.service.RegisterDefaultGroupService;

import com.anyex.apps.controller.openim.req.ReqRegisterDefaultGroup;
import com.anyex.apps.controller.openim.req.ReqRegisterDefaultGroupPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 注册默认群 控制器
 * <p>File：RegisterDefaultGroupController.java </p>
 * <p>Title: RegisterDefaultGroupController </p>
 * <p>Description:RegisterDefaultGroupController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim/registerDefaultGroup")
@Api(tags = "注册默认群")
public class RegisterDefaultGroupController extends GenericController
{
    @Autowired(required = false)
    private RegisterDefaultGroupService registerDefaultGroupService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

 /*   @GetMapping(value = "/findBy")
    @RequiresPermissions("openim:registerDefaultGroup:data")
    @ApiOperation(value = "根据ID取注册默认群", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, registerDefaultGroupService.selectByPrimaryKey(id));
    }*/

    @PostMapping(value = "/save")
    @RequiresPermissions("openim:registerDefaultGroup:operator")
    @ApiOperation(value = "保存注册默认群", httpMethod = "POST")
    @ApiImplicitParam(name = "groupId", value = "群ID", paramType = "form")
    public JsonMessage save(String groupId) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        RegisterDefaultGroup g = registerDefaultGroupService.findByGroupId(groupId);
        if(g != null)
        {
            throw new BusinessException("已添加");
        }

        GetGroupsInfoReq req = new GetGroupsInfoReq();
        req.getGroupIDs().add(groupId);
        GetGroupsInfoResp r = openImApiService.getGroupInfo(req);
        if(r.getGroupInfos()==null ||r.getGroupInfos().size()==0)
        {
            throw new BusinessException("群信息未找到");
        }

        g = new RegisterDefaultGroup();
        g.setGroupId(r.getGroupInfos().get(0).getGroupID());
        g.setGroupName(r.getGroupInfos().get(0).getGroupName());
        g.setGroupFaceUrl(r.getGroupInfos().get(0).getFaceURL());
        g.setId(SerialnoUtils.buildPrimaryKey());
        registerDefaultGroupService.insert(g);

        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("openim:registerDefaultGroup:data")
    @ApiOperation(value = "查询注册默认群", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqRegisterDefaultGroupPagination pagin) throws BusinessException
    {
        RegisterDefaultGroup entity = new RegisterDefaultGroup();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RegisterDefaultGroup> result = registerDefaultGroupService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("openim:registerDefaultGroup:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        registerDefaultGroupService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
