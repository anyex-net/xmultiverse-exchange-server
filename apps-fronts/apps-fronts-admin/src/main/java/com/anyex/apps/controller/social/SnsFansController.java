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

import com.anyex.apps.social.entity.SnsFans;
import com.anyex.apps.social.service.SnsFansService;

import com.anyex.apps.controller.social.req.ReqSnsFans;
import com.anyex.apps.controller.social.req.ReqSnsFansPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交粉丝(关注我的) 控制器
 * <p>File：SnsFansController.java </p>
 * <p>Title: SnsFansController </p>
 * <p>Description:SnsFansController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsFans")
@Api(tags = "社交粉丝(关注我的)")
public class SnsFansController extends GenericController
{
    @Autowired(required = false)
    private SnsFansService snsFansService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsFans:data")
    @ApiOperation(value = "根据ID取社交粉丝(关注我的)", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsFansService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsFans:operator")
    @ApiOperation(value = "保存社交粉丝(关注我的)", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsFans info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsFans entity = new SnsFans();
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
                snsFansService.insert(entity);
            } else {
                snsFansService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsFans:data")
    @ApiOperation(value = "查询社交粉丝(关注我的)", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSnsFansPagination pagin) throws BusinessException
    {
        SnsFans entity = new SnsFans();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsFans> result = snsFansService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsFans:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsFansService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
