/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.fund.entity.DepositTransHistory;
import com.anyex.apps.fund.service.DepositTransHistoryService;

import com.anyex.apps.controller.fund.req.ReqDepositTransHistory;
import com.anyex.apps.controller.fund.req.ReqDepositTransHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 充值交易历史 控制器
 * <p>File：DepositTransHistoryController.java </p>
 * <p>Title: DepositTransHistoryController </p>
 * <p>Description:DepositTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/depositTransHistory")
@Api(tags = "充值交易历史")
public class DepositTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private DepositTransHistoryService depositTransHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("fund:depositTransHistory:data")
    @ApiOperation(value = "根据ID取充值交易历史", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, depositTransHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("fund:depositTransHistory:operator")
    @ApiOperation(value = "保存充值交易历史", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqDepositTransHistory info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            DepositTransHistory entity = new DepositTransHistory();
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
                depositTransHistoryService.insert(entity);
            } else {
                depositTransHistoryService.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("fund:depositTransHistory:data")
    @ApiOperation(value = "查询充值交易历史", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqDepositTransHistoryPagination pagin) throws BusinessException
    {
        DepositTransHistory entity = new DepositTransHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<DepositTransHistory> result = depositTransHistoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("fund:depositTransHistory:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        depositTransHistoryService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
