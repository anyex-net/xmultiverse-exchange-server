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

import com.anyex.apps.rwa.entity.RwaCertInstInvestor;
import com.anyex.apps.rwa.service.RwaCertInstInvestorService;

import com.anyex.apps.controller.rwa.req.ReqRwaCertInstInvestor;
import com.anyex.apps.controller.rwa.req.ReqRwaCertInstInvestorPagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * RWA认证机构投资者 控制器
 * <p>File：RwaCertInstInvestorController.java </p>
 * <p>Title: RwaCertInstInvestorController </p>
 * <p>Description:RwaCertInstInvestorController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaCertInstInvestor")
@Api(tags = "RWA认证机构投资者")
public class RwaCertInstInvestorController extends GenericController
{
    @Autowired(required = false)
    private RwaCertInstInvestorService rwaCertInstInvestorService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("rwa:rwaCertInstInvestor:data")
    @ApiOperation(value = "根据ID取RWA认证机构投资者", httpMethod = "GET")
    public JsonMessage<RwaCertInstInvestor> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaCertInstInvestorService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("rwa:rwaCertInstInvestor:data")
    @ApiOperation(value = "查询RWA认证机构投资者", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaCertInstInvestor>> data(@ModelAttribute ReqRwaCertInstInvestorPagination pagin) throws BusinessException
    {
        RwaCertInstInvestor entity = new RwaCertInstInvestor();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<RwaCertInstInvestor> result = rwaCertInstInvestorService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    @PostMapping(value = "/save")
//    @RequiresPermissions("rwa:rwaCertInstInvestor:operator")
//    @ApiOperation(value = "保存RWA认证机构投资者", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute ReqRwaCertInstInvestor info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            RwaCertInstInvestor entity = new RwaCertInstInvestor();
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
//                rwaCertInstInvestorService.insert(entity);
//            } else {
//                rwaCertInstInvestorService.updateByPrimaryKey(entity);
//            }
//        }
//        return json;
//    }
//
//    @PostMapping(value = "/del")
//    @RequiresPermissions("rwa:rwaCertInstInvestor:operator")
//    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
//    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
//    public JsonMessage del(String ids) throws BusinessException
//    {
//        rwaCertInstInvestorService.removeBatch(ids.split(","));
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
