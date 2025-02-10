/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.base;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.base.entity.UserInstTradeFee;
import com.anyex.apps.base.service.UserInstTradeFeeService;

import com.anyex.apps.controller.base.req.ReqUserInstTradeFee;
import com.anyex.apps.controller.base.req.ReqUserInstTradeFeePagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 用户交易手续费费率 控制器
 * <p>File：UserInstTradeFeeController.java </p>
 * <p>Title: UserInstTradeFeeController </p>
 * <p>Description:UserInstTradeFeeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/base/userInstTradeFee")
@Api(tags = "用户交易手续费费率")
public class UserInstTradeFeeController extends GenericController
{
    @Autowired(required = false)
    private UserInstTradeFeeService userInstTradeFeeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("base:userInstTradeFee:data")
    @ApiOperation(value = "根据ID取用户交易手续费费率", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, userInstTradeFeeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("base:userInstTradeFee:operator")
    @ApiOperation(value = "保存用户交易手续费费率", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqUserInstTradeFee info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserInstTradeFee entity = new UserInstTradeFee();
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
                userInstTradeFeeService.insert(entity);
            } else {
                userInstTradeFeeService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("base:userInstTradeFee:data")
    @ApiOperation(value = "查询用户交易手续费费率", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqUserInstTradeFeePagination pagin) throws BusinessException
    {
        UserInstTradeFee entity = new UserInstTradeFee();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<UserInstTradeFee> result = userInstTradeFeeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("base:userInstTradeFee:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        userInstTradeFeeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
