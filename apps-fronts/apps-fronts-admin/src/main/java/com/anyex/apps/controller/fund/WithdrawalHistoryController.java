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

import com.anyex.apps.fund.entity.WithdrawalHistory;
import com.anyex.apps.fund.service.WithdrawalHistoryService;

import com.anyex.apps.controller.fund.req.ReqWithdrawalHistory;
import com.anyex.apps.controller.fund.req.ReqWithdrawalHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 提现历史 控制器
 * <p>File：WithdrawalHistoryController.java </p>
 * <p>Title: WithdrawalHistoryController </p>
 * <p>Description:WithdrawalHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/withdrawalHistory")
@Api(tags = "提现历史")
public class WithdrawalHistoryController extends GenericController
{
    @Autowired(required = false)
    private WithdrawalHistoryService withdrawalHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("fund:withdrawalHistory:data")
    @ApiOperation(value = "根据ID取提现历史", httpMethod = "GET")
    public JsonMessage<WithdrawalHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, withdrawalHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("fund:withdrawalHistory:data")
    @ApiOperation(value = "查询提现历史", httpMethod = "POST")
    public JsonMessage<PaginateResult<WithdrawalHistory>> data(@ModelAttribute ReqWithdrawalHistoryPagination pagin) throws BusinessException
    {
        WithdrawalHistory entity = new WithdrawalHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<WithdrawalHistory> result = withdrawalHistoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("fund:withdrawalHistory:operator")
//    @ApiOperation(value = "保存提现历史", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqWithdrawalHistory info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            WithdrawalHistory entity = new WithdrawalHistory();
//            BeanUtils.copyProperties(info, entity);
//            //
//            if (null == info.getId())
//            {
//                entity.setCreateTime(System.currentTimeMillis());
//            }
//            entity.setUpdateTime(System.currentTimeMillis());
//            //
//            log.info("entity:{}", entity);
//            if(null == entity.getId()){
//                withdrawalHistoryService.insert(entity);
//            } else {
//                withdrawalHistoryService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("fund:withdrawalHistory:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        withdrawalHistoryService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
