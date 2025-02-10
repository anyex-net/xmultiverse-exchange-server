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

import com.anyex.apps.fund.entity.BalancesTransHistory;
import com.anyex.apps.fund.service.BalancesTransHistoryService;

import com.anyex.apps.controller.fund.req.ReqBalancesTransHistory;
import com.anyex.apps.controller.fund.req.ReqBalancesTransHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 资金账户交易历史 控制器
 * <p>File：BalancesTransHistoryController.java </p>
 * <p>Title: BalancesTransHistoryController </p>
 * <p>Description:BalancesTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/fund/balancesTransHistory")
@Api(tags = "资金账户交易历史")
public class BalancesTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private BalancesTransHistoryService balancesTransHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("fund:balancesTransHistory:data")
    @ApiOperation(value = "根据ID取资金账户交易历史", httpMethod = "GET")
    public JsonMessage<BalancesTransHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, balancesTransHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("fund:balancesTransHistory:data")
    @ApiOperation(value = "查询资金账户交易历史", httpMethod = "POST")
    public JsonMessage<PaginateResult<BalancesTransHistory>> data(@ModelAttribute ReqBalancesTransHistoryPagination pagin) throws BusinessException
    {
        BalancesTransHistory entity = new BalancesTransHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<BalancesTransHistory> result = balancesTransHistoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("fund:balancesTransHistory:operator")
//    @ApiOperation(value = "保存资金账户交易历史", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqBalancesTransHistory info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            BalancesTransHistory entity = new BalancesTransHistory();
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
//                balancesTransHistoryService.insert(entity);
//            } else {
//                balancesTransHistoryService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("fund:balancesTransHistory:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        balancesTransHistoryService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
