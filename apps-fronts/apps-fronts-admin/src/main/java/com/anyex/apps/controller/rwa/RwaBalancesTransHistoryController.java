/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.rwa.entity.RwaBalancesTransHistory;
import com.anyex.apps.rwa.service.RwaBalancesTransHistoryService;

import com.anyex.apps.controller.rwa.req.ReqRwaBalancesTransHistory;
import com.anyex.apps.controller.rwa.req.ReqRwaBalancesTransHistoryPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA账户交易历史 控制器
 * <p>File：RwaBalancesTransHistoryController.java </p>
 * <p>Title: RwaBalancesTransHistoryController </p>
 * <p>Description:RwaBalancesTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaBalancesTransHistory")
@Api(tags = "RWA账户交易历史")
public class RwaBalancesTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaBalancesTransHistory:data")
    @ApiOperation(value = "根据ID取RWA账户交易历史", httpMethod = "GET")
    public JsonMessage<RwaBalancesTransHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaBalancesTransHistoryService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaBalancesTransHistory:data")
    @ApiOperation(value = "查询RWA账户交易历史", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaBalancesTransHistory>> data(@ModelAttribute ReqRwaBalancesTransHistoryPagination pagin) throws BusinessException
    {
        RwaBalancesTransHistory entity = new RwaBalancesTransHistory();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaBalancesTransHistory> result = rwaBalancesTransHistoryService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaBalancesTransHistory:operator")
//    @ApiOperation(value = "保存RWA账户交易历史", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaBalancesTransHistory info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaBalancesTransHistory entity = new RwaBalancesTransHistory();
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
//                rwaBalancesTransHistoryService.insert(entity);
//            } else {
//                rwaBalancesTransHistoryService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaBalancesTransHistory:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaBalancesTransHistoryService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
