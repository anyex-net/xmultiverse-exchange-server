/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.spot.entity.SliceHistory;
import com.anyex.apps.spot.service.SliceHistoryService;

import com.anyex.apps.controller.spot.req.ReqSliceHistory;
import com.anyex.apps.controller.spot.req.ReqSliceHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * slice_history 控制器
 * <p>File：SliceHistoryController.java </p>
 * <p>Title: SliceHistoryController </p>
 * <p>Description:SliceHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/sliceHistory")
@Api(description = "slice_history")
public class SliceHistoryController extends GenericController
{
    @Autowired(required = false)
    private SliceHistoryService sliceHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:sliceHistory:data")
    @ApiOperation(value = "根据ID取slice_history", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, sliceHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:sliceHistory:data")
    @ApiOperation(value = "查询slice_history", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSliceHistoryPagination pagin) throws BusinessException
    {
        SliceHistory entity = new SliceHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SliceHistory> result = sliceHistoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("spot:sliceHistory:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        sliceHistoryService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
