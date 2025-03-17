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
import com.anyex.apps.spot.entity.DealHistory;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.spot.service.DealHistoryService;

import com.anyex.apps.controller.spot.req.ReqDealHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * deal_history_example 控制器
 * <p>File：DealHistoryExampleController.java </p>
 * <p>Title: DealHistoryExampleController </p>
 * <p>Description:DealHistoryExampleController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/spot/dealHistory")
@Api(description = "deal_history")
public class DealHistoryController extends GenericController {
    @Autowired(required = false)
    private DealHistoryService dealHistoryExampleService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("spot:dealHistoryExample:data")
    @ApiOperation(value = "根据ID取deal_history_example", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, dealHistoryExampleService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("spot:dealHistory:data")
    @ApiOperation(value = "查询deal_history", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqDealHistoryPagination pagin, String tableName) throws BusinessException {
        DealHistory entity = new DealHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<DealHistory> result = dealHistoryExampleService.selectList(pagin, entity,tableName);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
