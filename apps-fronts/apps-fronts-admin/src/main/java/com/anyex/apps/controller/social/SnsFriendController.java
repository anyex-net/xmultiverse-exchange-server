/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.social.entity.SnsFriend;
import com.anyex.apps.social.service.SnsFriendService;

import com.anyex.apps.controller.social.req.ReqSnsFriend;
import com.anyex.apps.controller.social.req.ReqSnsFriendPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交好友 控制器
 * <p>File：SnsFriendController.java </p>
 * <p>Title: SnsFriendController </p>
 * <p>Description:SnsFriendController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsFriend")
@Api(tags = "社交好友")
public class SnsFriendController extends GenericController
{
    @Autowired(required = false)
    private SnsFriendService snsFriendService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsFriend:data")
    @ApiOperation(value = "根据ID取社交好友", httpMethod = "GET")
    public JsonMessage<SnsFriend> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsFriendService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsFriend:operator")
    @ApiOperation(value = "保存社交好友", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsFriend info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsFriend entity = new SnsFriend();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                snsFriendService.insert(entity);
            } else {
                snsFriendService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsFriend:data")
    @ApiOperation(value = "查询社交好友", httpMethod = "POST")
    public JsonMessage<PaginateResult<SnsFriend>> data(@ModelAttribute ReqSnsFriendPagination pagin) throws BusinessException
    {
        SnsFriend entity = new SnsFriend();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsFriend> result = snsFriendService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsFriend:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsFriendService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
