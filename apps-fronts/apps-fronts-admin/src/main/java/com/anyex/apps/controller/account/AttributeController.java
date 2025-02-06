/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.service.AttributeService;

import com.anyex.apps.controller.account.req.ReqAttribute;
import com.anyex.apps.controller.account.req.ReqAttributePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 用户属性表 控制器
 * <p>File：AttributeController.java </p>
 * <p>Title: AttributeController </p>
 * <p>Description:AttributeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/attribute")
@Api(tags = "帐户用户属性")
public class AttributeController extends GenericController
{
    @Autowired(required = false)
    private AttributeService attributeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:attribute:data")
    @ApiOperation(value = "根据ID取用户属性", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, attributeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:attribute:operator")
    @ApiOperation(value = "保存用户属性", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqAttribute info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            Attribute entity = new Attribute();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(new Date());
            }
            entity.setChangeTime(new Date());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                attributeService.insert(entity);
            } else {
                attributeService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:attribute:data")
    @ApiOperation(value = "查询用户属性", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqAttributePagination pagin) throws BusinessException
    {
        Attribute entity = new Attribute();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<Attribute> result = attributeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:attribute:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        attributeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
